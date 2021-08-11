package com.example.supportping;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MypageMainAdapter extends RecyclerView.Adapter<MypageMainAdapter.CustomViewHolder> {

    Context mCtx;
    private ArrayList<MainData> arraylist;
    public static String id;
    public static String editTitle;
    public static String editContent;
    public static String editPeople;
    public static String editNowLocation;

    public static boolean editStatus = false;

    public MypageMainAdapter(ArrayList<MainData> arraylist, Context mCtx) { // 생성자
        this.arraylist = arraylist;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public MypageMainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view2, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MypageMainAdapter.CustomViewHolder holder, int position) {
        holder.tv_title.setText(arraylist.get(position).getTitle());
        holder.tv_nickname.setText(arraylist.get(position).getNickname());
        holder.tv_personnel.setText(arraylist.get(position).getPersonnel());
        holder.tv_place.setText(arraylist.get(position).getPlace());
        holder.tv_content.setText(arraylist.get(position).getContent());
        holder.tv_personnel.setText(arraylist.get(position).getPersonnel());

        holder.btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현명히 뷰 자세히 보기
            }
        });

        holder.itemView.setTag(position);

        holder.ib_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 팝업 메뉴
                PopupMenu popupMenu = new PopupMenu(mCtx, v);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        // 수정 클릭
                            if (item.getItemId() == R.id.action_edit) {
                            // 데이터 저장
                            editTitle = arraylist.get(position).getTitle();
                            editContent = arraylist.get(position).getContent();
                            editNowLocation = arraylist.get(position).getPlace();
                            editPeople = arraylist.get(position).getPersonnel();

                            editStatus = true;
                            id = arraylist.get(position).getNickname();

                            Intent intent = new Intent(v.getContext(), PostActivity.class);
                            v.getContext().startActivity(intent);
                        } // 삭제 클릭
                        else if (item.getItemId() == R.id.action_delete) {

                            // 뷰 (서버 게시물 아이디)
                            id = arraylist.get(position).getNickname();
                            HomeActivity.deletePost(id);
                            remove(holder.getAdapterPosition());

                        }
                        return false;
                    }


                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arraylist ? arraylist.size() : 0);
    }

    public void remove(int position) {
        try {
            arraylist.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected TextView tv_nickname;
        protected TextView tv_personnel;
        protected TextView tv_place;
        protected TextView tv_content;
        protected Button btn_check;
        protected ImageButton ib_more;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
            this.tv_personnel = (TextView) itemView.findViewById(R.id.tv_personnel);
            this.tv_place = (TextView) itemView.findViewById(R.id.tv_place);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            this.btn_check = (Button) itemView.findViewById(R.id.btn_check);
            this.ib_more = (ImageButton) itemView.findViewById(R.id.ib_more);
        }
    }
}
