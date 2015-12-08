package com.example.hoang.bookmovietickets.Helper;

/**
 * Created by hoang on 11/16/2015.
 */
public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);

}
