package com.fatmanurrr.gmail;

/**
 * Created by Lenovo on 17.08.2018.
 */
//burda p yi init etmemişiz yani değer atamamışız

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.MotionEvent;
import android.view.View;

import static android.support.v7.widget.helper.ItemTouchHelper.*;

public class SwipeController extends ItemTouchHelper.Callback {

    //  private RecyclerItemTouchHelperListener listener;
    private Paint p = null;//:D olmaz null olmaz :D niyeki çünkü null da boş değer  e tamam ilki boş aşağıda atamayacakmıyız atama yapacaksak null değer vermeye gerek yok .burda değişkeni  oluşturulmuş içine değer atılmamış (NullPointerException hataları değer atanmayan durumlarda olur)
    private Context context;

    private SwipeListener mSwipeListener;

    public SwipeController(SwipeListener mSwipeListener, Context context) {
        this.context = context;
        this.mSwipeListener = mSwipeListener;
    }

    //* public SwipeController(RecyclerItemTouchHelperListener listener)
    //  {
    //    this.listener=listener;
    //}


    private MailAdapter madapter;

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {  //yönünü belirler
        return makeMovementFlags(0, LEFT | RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mSwipeListener.onSwipe(viewHolder.getAdapterPosition(), direction);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;

    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


     /* public int convertToAbsoluteDirection(int flag,int layouDirection)
    {
        boolean swipeBack;
        if(swipeBack)


    } */

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {


     //   Context context = null;
        Bitmap icon;

        if (actionState == ACTION_STATE_SWIPE) {
            p = new Paint();
            View itemView = viewHolder.itemView;
            float height = (float) itemView.getBottom() - (float) itemView.getTop();
            float width = height / 3;

            if (dX > 0) {
                p.setColor(Color.parseColor("#388E3C"));
                RectF backround = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                c.drawRect(backround, p);
                icon = BitmapFactory.decodeResource(recyclerView.getContext().getResources(), R.drawable.ic_star_purple_a700_24dp);
                RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                c.drawBitmap(icon, null, icon_dest, p);

            } else {
                p.setColor(Color.parseColor("#D32F2F"));
                RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                c.drawRect(background, p);
                icon = BitmapFactory.decodeResource(recyclerView.getContext().getResources(), R.drawable.ic_delete_purple_a700_24dp);
                RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                c.drawBitmap(icon, null, icon_dest, p);
            }
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

    }

}

