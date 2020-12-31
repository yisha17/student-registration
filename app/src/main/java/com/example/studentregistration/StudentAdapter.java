package com.example.studentregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private OnItemClickListener listener;

    Context context;
    List<Student> students = new ArrayList<>();

    StudentAdapter(Context context){
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = students.get(position);
        holder.full_name.setText(student.getName());
        holder.gpa.setText(String.valueOf(student.getGpa()));
        holder.id.setText(student.getId());
        holder.department.setText(student.getDepartment());
        holder.school.setText(student.getSchool());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
    void setList(List<Student> students){
        this.students = students;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView full_name,gpa,school,department,id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            full_name = itemView.findViewById(R.id.full_name);
            gpa = itemView.findViewById(R.id.gpa);
            school = itemView.findViewById(R.id.school);
            department = itemView.findViewById(R.id.department);
            id = itemView.findViewById(R.id.id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(students.get(position));
                    }

                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Student student);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
