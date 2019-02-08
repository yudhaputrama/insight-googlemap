package org.d3ifcool.googlemap;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder> {

    private Context mContext;
    private List<Building> mList;
    private OnItemClickListener mListener;

    BuildingAdapter(Context context, List<Building> list){
        mContext = context;
        mList = list ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_destination, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Building building = mList.get(position);

        holder.name.setText(building.name);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null){
                    mListener.onItemClick(view, building, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;
        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.building_icon);
            name = itemView.findViewById(R.id.building_name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_destination);
        }
    }


    public void setOnClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    interface OnItemClickListener {
        void onItemClick (View view,Building building,int position);
    }
}
