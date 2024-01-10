package com.example.doan_nhom1a;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.grpc.okhttp.internal.Util;

public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteViewHolder> {
    Context context;

    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        holder.titleTextView.setText(note.title);
        holder.contentTextView.setText(note.content);
        holder.timestampTextView.setText(Utility.timestampToString(note.timestamp));

        holder.itemView.setOnClickListener((v)->{
            //chuyển từ activity này sang activity add NoteDetail
            Intent intent = new Intent(context,NoteDetailsActivity.class);
            //lưu tiêu đề vào biến internt "title", nội dung là title của diary
            intent.putExtra("title",note.title);
            //lưu nội dung vào biến internt "content", nội dung là content của diary
            intent.putExtra("content",note.content);
            //vì item diary ko có id, nên phải láy id của snapshot ( tạm hiểu là id của diary )
            //gán vào biến docID
            String docId = this.getSnapshots().getSnapshot(position).getId();
            //lưu vào biến id tương tự như 2 cái trên
            intent.putExtra("docId",docId);
            //chyển activity
            context.startActivity(intent);
        }
        );

    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item,parent,false);
        return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView,contentTextView,timestampTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.note_title_text_view);
            contentTextView = itemView.findViewById(R.id.note_content_text_view);
            timestampTextView = itemView.findViewById(R.id.note_timestamp_text_view);
        }
    }
}
