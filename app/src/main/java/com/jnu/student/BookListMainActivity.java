package com.jnu.student;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnu.student.data.Book;

import java.util.ArrayList;

public class BookListMainActivity extends AppCompatActivity {

    public static final int MENU_ID_ADD = 1;
    public static final int MENU_ID_UPDATE = 2;
    public static final int MENU_ID_DELETE = 3;
    public ArrayList<Book> BookItems;
    private MainRecycleViewAdapter mainRecycleViewAdapter;

    private final ActivityResultLauncher<Intent> addDataLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result ->{
                if (null!=result){
                    Intent intent=result.getData();
                    if (result.getResultCode() == EditBookActivity.RESULT_CODE_SUCCESS) {
                        Bundle bundle=intent.getExtras();
                        String title=bundle.getString("title");
                        int position=bundle.getInt("position");
                        BookItems.add(position,new Book(title,R.drawable.book_no_name));
                        mainRecycleViewAdapter.notifyItemInserted(position);
                    }
//                    BookItems.add(item.getOrder(),new Book("added",R.drawable.ic_launcher_background));
                }
            });
    private final ActivityResultLauncher<Intent> updateDataLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result ->{
                if (null!=result){
                    Intent intent=result.getData();
                    if (result.getResultCode() == EditBookActivity.RESULT_CODE_SUCCESS) {
                        Bundle bundle=intent.getExtras();
                        String title=bundle.getString("title");
                        int position=bundle.getInt("position");
                        BookItems.get(position).setTitle(title);
                        mainRecycleViewAdapter.notifyItemChanged(position);

                    }
//                    BookItems.add(item.getOrder(),new Book("added",R.drawable.ic_launcher_background));
                }
            });

    //    private TextView textViewHello;
//    private TextView textViewWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到recyclerView对象
        RecyclerView recyclerViewMain=findViewById(R.id.recycleview_main);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        RecyclerView 中的列表项由 LayoutManager 类负责排列
        recyclerViewMain.setLayoutManager(linearLayoutManager);

        BookItems = new ArrayList<>();
        BookItems.add(new Book("信息安全数学基础(第2版）",R.drawable.book_1));
        BookItems.add(new Book("软件项目管理案例教程（第2版）",R.drawable.book_2));
        BookItems.add(new Book("创新工程实践",R.drawable.book_no_name));


//      传递数据并创建Adapter对象，并绑定到RecycleView
        mainRecycleViewAdapter = new MainRecycleViewAdapter(BookItems);
        recyclerViewMain.setAdapter(mainRecycleViewAdapter);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case MENU_ID_ADD:
                Intent intent = new Intent(this, EditBookActivity.class);
                intent.putExtra("position",item.getOrder());
                addDataLauncher.launch(intent);
                break;
            case MENU_ID_UPDATE:
//                Toast.makeText(this,"item update "+item.getOrder()+" clicked!",Toast.LENGTH_LONG).show();
                Intent intentUpdate = new Intent(this, EditBookActivity.class);
                intentUpdate.putExtra("position",item.getOrder());
                intentUpdate.putExtra("title",BookItems.get(item.getOrder()).getTitle());
                updateDataLauncher.launch(intentUpdate);
                break;
            case MENU_ID_DELETE:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.string_confirmation)
                        .setMessage(R.string.sure_to_delete)
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                BookItems.remove(item.getOrder());
                                mainRecycleViewAdapter.notifyItemRemoved(item.getOrder());
                            }
                        }).create();
                alertDialog.show();
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
                contextMenu.add(0,MENU_ID_ADD,getAdapterPosition(),"新建 "+getAdapterPosition());
                contextMenu.add(0,MENU_ID_UPDATE,getAdapterPosition(),"修改 "+getAdapterPosition());
                contextMenu.add(0,MENU_ID_DELETE,getAdapterPosition(),"删除 "+getAdapterPosition());

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
            viewHolder.getTextView().setText(localDataSet.get(position).getTitle());
            viewHolder.getImageViewImage().setImageResource( localDataSet.get(position).getResourceId());

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }

}