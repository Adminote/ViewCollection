package com.android.martino.customviewset.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.mOnItemClickLitener = onItemClickLitener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    protected void handleClickEvent(final RecyclerView.ViewHolder holder) {
        handleOnClick(holder);
        handleOnLongClick(holder);
    }

    private void handleOnClick(final RecyclerView.ViewHolder holder) {
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    private void handleOnLongClick(final RecyclerView.ViewHolder holder) {

        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

    }
}
