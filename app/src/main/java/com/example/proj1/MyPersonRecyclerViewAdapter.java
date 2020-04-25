package com.example.proj1;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proj1.PersonFragment.OnListFragmentInteractionListener;
import com.example.proj1.persons.PersonsListContent;

import java.util.List;


public class MyPersonRecyclerViewAdapter extends RecyclerView.Adapter<MyPersonRecyclerViewAdapter.ViewHolder> {

    private final List<PersonsListContent.Person> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyPersonRecyclerViewAdapter(List<PersonsListContent.Person> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        PersonsListContent.Person person=mValues.get(position);
        holder.mItem=person;

        holder.mContentView.setText(String.format("%s %s", person.name, person.surname));

        final String picPath=person.picPath;
        Context context=holder.mView.getContext();
        if(picPath!=null && !picPath.isEmpty()){

                Drawable avatarPath;
                avatarPath=context.getResources().getDrawable(context.getResources().getIdentifier(picPath, "drawable", context.getPackageName()));
                holder.mItemImageView.setImageDrawable(avatarPath);

        }else{
            Drawable taskDrawable=context.getResources().getDrawable(R.drawable.avatar_1);
            holder.mItemImageView.setImageDrawable(taskDrawable);
        }

        holder.mView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(null !=mListener){
                    mListener.onListFragmentClickInteraction(holder.mItem,position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v){
                mListener.onListFragmentLongClickInteraction(position);
                return false;
            }
        });

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mListener.onDeleteButtonClickInteraction(holder.mItem,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public PersonsListContent.Person mItem;
        public final ImageView mItemImageView;
        public final ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mItemImageView=view.findViewById(R.id.item_image);
            mContentView = (TextView) view.findViewById(R.id.content);
            mDeleteButton=view.findViewById(R.id.delete_button);
        }


        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}
