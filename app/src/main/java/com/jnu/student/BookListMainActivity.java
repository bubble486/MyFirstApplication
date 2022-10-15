package com.jnu.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookListMainActivity extends AppCompatActivity {

    public static final int MENU_ID_ADD = 1;
    public static final int MENU_ID_UPDATE = 2;
    public static final int MENU_ID_DELETE = 3;

    //    private TextView textViewHello;
//    private TextView textViewWorld;
    static class Book{
        private final String title;
//        private final String coverResource;

        Book(String title){
            this.title = title;
//            this.coverResource = coverResource;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Book> mainBookSet;

        //找到recyclerView对象
        RecyclerView recyclerViewMain=findViewById(R.id.recycleview_main);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        RecyclerView 中的列表项由 LayoutManager 类负责排列
        recyclerViewMain.setLayoutManager(linearLayoutManager);

        Book book1 = new Book("信息安全数学基础(第2版）");
        Book book2 = new Book("软件项目管理案例教程（第2版）");
        Book book3 = new Book("创新工程实践");
        mainBookSet = new ArrayList<>();
        mainBookSet.add(book1);
        mainBookSet.add(book2);
        mainBookSet.add(book3);


//      传递数据并创建Adapter对象，并绑定到RecycleView
        MainRecycleViewAdapter mainRecycleViewAdapter = new MainRecycleViewAdapter(mainBookSet);
        recyclerViewMain.setAdapter(mainRecycleViewAdapter);

////        通过id找到TextView
//        textViewHello = this.findViewById(R.id.text_view_hello);
//        textViewWorld = this.findViewById(R.id.text_view_world);
//        Button buttonExchange = this.findViewById(R.id.button_exchange);
//
////        String stringHelloWorld = this.getResources().getText(R.string.string_hello).toString();
////        textViewHello.setText(stringHelloWorld);
//
//        buttonExchange.setOnClickListener(new View.OnClickListener() {
////            匿名类只用一次不想取名字，父类是View.OnClickListener
//            @Override
//            public void onClick(View view) {
//                Log.i("test","hello");
////                textViewHello.setText(getText(R.string.string_chinese).toString());
//            }
//        });
//        ClickListener listener = new ClickListener();
//        buttonExchange.setOnClickListener(listener);
////        buttonEnglish.setOnClickListener(listener);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case MENU_ID_ADD:
                Toast.makeText(this,"item add "+item.getOrder()+" clicked!",Toast.LENGTH_LONG).show();
                break;
            case MENU_ID_UPDATE:
                Toast.makeText(this,"item update "+item.getOrder()+" clicked!",Toast.LENGTH_LONG).show();
                break;
            case MENU_ID_DELETE:
                Toast.makeText(this,"item delete "+item.getOrder()+" clicked!",Toast.LENGTH_LONG).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    public static class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {

        private final ArrayList<Book> localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
            private final TextView textView;
            private final ImageView imageViewImage;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View
                imageViewImage = view.findViewById(R.id.image_view_book_cover);
                textView = (TextView) view.findViewById(R.id.text_view_book_title);

                view.setOnCreateContextMenuListener(this);
            }

            public TextView getTextView() {
                return textView;
            }
            public ImageView getImageViewImage() { return imageViewImage; }

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0,MENU_ID_ADD,getAdapterPosition(),"Add "+getAdapterPosition());
                contextMenu.add(0,MENU_ID_UPDATE,getAdapterPosition(),"Update "+getAdapterPosition());
                contextMenu.add(0,MENU_ID_DELETE,getAdapterPosition(),"Delete "+getAdapterPosition());

            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        public MainRecycleViewAdapter(ArrayList<Book> dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_main, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            // Get element from your dataset at this position and replace the
            // contents of the view with that element
//            viewHolder.getTextView().setText(localDataSet.get(position));
            viewHolder.getTextView().setText(localDataSet.get(position).title);
            if(position==0)
                viewHolder.getImageViewImage().setImageResource( R.drawable.book_1 );
            if (position==1)
                viewHolder.getImageViewImage().setImageResource( R.drawable.book_2 );
            if (position==2)
                viewHolder.getImageViewImage().setImageResource( R.drawable.book_no_name );

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }



//    private class ClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View clickedButtonView) {
////            安卓对于按钮的大小写有自己的处理
////            textViewHello.setText(((Button)clickedButtonView).getText());
////            Log.v("test",((Button)clickedButtonView).getText().toString());
////            按动两个按钮之后可以获取按钮上面的文字
////            if(clickedButtonView== buttonExchange){
////                textViewHello.setText(R.string.string_chinese);
////            }
////            else if(clickedButtonView==buttonEnglish){
////                textViewHelloWorld.setText(R.string.string_english);
////            }
//            String temp = textViewHello.getText().toString();
//            textViewHello.setText(textViewWorld.getText().toString());
//            textViewWorld.setText(temp);
//        }
//    }
}