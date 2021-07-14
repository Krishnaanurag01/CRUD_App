package com.example.crudapp.RecycleViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudapp.Contact.Contact;
import com.example.crudapp.DisplayContacts;
import com.example.crudapp.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rows,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.name.setText(contact.getName());
        holder.number.setText(contact.getNumber());
//        holder.number.setText(contact.getId());


    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView number;
        private TextView id;

        public ViewHolder( View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name=itemView.findViewById(R.id.rowText02);
            number=itemView.findViewById(R.id.rowText01);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            Contact contact = contactList.get(position);
            Toast.makeText(context,"Name and Position is : "+ contact.getName() +" , "+position,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, DisplayContacts.class);
            intent.putExtra("Name" ,contact.getName());
            intent.putExtra("Number" ,contact.getNumber());
            context.startActivity(intent);


        }
    }
}
