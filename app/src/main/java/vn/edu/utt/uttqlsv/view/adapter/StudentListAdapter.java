package vn.edu.utt.uttqlsv.view.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.utt.uttqlsv.R;
import vn.edu.utt.uttqlsv.model.Gender;
import vn.edu.utt.uttqlsv.model.Student;
import vn.edu.utt.uttqlsv.view.activities.StudentList;
import vn.edu.utt.uttqlsv.view.fragments.StudentDetail;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {

    public int clickedPosition = 0;
    private final StudentList activity;
    private final ArrayList<Student> studentList;

    public StudentListAdapter(StudentList activity, ArrayList<Student> studentList) {
        this.activity = activity;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.student_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.studentCodeTV.setText(student.getStudentCode());
        holder.studentNameTV.setText(student.getName());

        if (student.isGender() == Gender.MALE)
            holder.genderTV.setText(activity.getString(R.string.male));
        else holder.genderTV.setText(activity.getString(R.string.female));

        holder.dobTV.setText(student.getDateOfBirth());
        holder.classNameTV.setText(student.getClassName());

        holder.setOnClickListener((view, position1) -> openStudentDetailFragment(student.getStudentCode()));

        holder.itemView.setOnLongClickListener(v -> {
            clickedPosition = holder.getAbsoluteAdapterPosition();
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    private void openStudentDetailFragment(String studentCode) {
        StudentDetail studentDetailFragment = StudentDetail.newInstance(studentCode);
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.student_detail_fragment_container, studentDetailFragment);
        transaction.addToBackStack("on top of backstack").commit();

    }

    interface ItemClickListener {
        void onClick(View view, int position);
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnClickListener {
        public TextView studentCodeTV;
        public TextView studentNameTV;
        public TextView genderTV;
        public TextView dobTV;
        public TextView classNameTV;
        public ItemClickListener clickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentCodeTV = itemView.findViewById(R.id.student_code_tv);
            studentNameTV = itemView.findViewById(R.id.student_name_tv);
            genderTV = itemView.findViewById(R.id.gender_tv);
            dobTV = itemView.findViewById(R.id.date_of_birth_tv);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Action menu");
            menu.add(Menu.NONE, R.id.context_menu_action_edit, Menu.NONE, "Edit");
            menu.add(Menu.NONE, R.id.context_menu_action_delete, Menu.NONE, "Delete");
            menu.getItem(0).setIcon(R.drawable.ic_edit);
            menu.getItem(1).setIcon(R.drawable.ic_delete);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAbsoluteAdapterPosition());
        }

        public void setOnClickListener(ItemClickListener listener) {
            clickListener = listener;
        }


    }

    public int getClickedPosition() {
        return clickedPosition;
    }
}
