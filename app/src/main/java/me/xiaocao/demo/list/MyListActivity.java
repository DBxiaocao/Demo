package me.xiaocao.demo.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaocao.demo.R;
import me.xiaocao.demo.base.BaseViewHolder;
import me.xiaocao.demo.base.MyListAdapter;

public class MyListActivity extends AppCompatActivity {

    @Bind(R.id.listview)
    ListView listview;
    private MyListAdapter<ListBean> listadapter;
    private List<ListBean> alllists=new ArrayList<>();
    private String gridTitle[]=new String[]{"Grid1","Grid2","Grid3","Grid4","Grid5","Grid6"};
    private String listTitle[]=new String[]{"list1","list2","list3","list4","list5","list6"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        ButterKnife.bind(this);
        initDate();
    }

    private void initDate() {
        for (int i = 0; i <4 ; i++) {
            ListBean bean=new ListBean();
            List<String> lists=new ArrayList<>();
            List<String> grids=new ArrayList<>();
            for (int j = 0; j < listTitle.length; j++) {
                lists.add("标题"+i+listTitle[j]);
            }
            bean.lists=lists;
            for (int j = 0; j < gridTitle.length; j++) {
                grids.add("标题"+i+gridTitle[j]);
            }
            bean.grids=grids;
            bean.title="Title"+i;
            alllists.add(bean);
        }
        listadapter=new MyListAdapter<>(this, alllists,R.layout.item_list);
        listadapter.setOnCallBackData(new MyListAdapter.OnCallBackData<ListBean>() {
            @Override
            public void convertView(BaseViewHolder helper, ListBean item) {
                helper.setText(R.id.title,item.title);
                MyGridView grid=helper.getView(R.id.grid);
                if (item.grids!=null && item.grids.size()>0){
                    List<String> grids=item.grids;
                    MyListAdapter<String> gridAdapter=new MyListAdapter<>(MyListActivity.this,grids,R.layout.item_grid);
                    gridAdapter.setOnCallBackData(new MyListAdapter.OnCallBackData<String>() {
                        @Override
                        public void convertView(BaseViewHolder helper, String item) {
                            helper.setText(R.id.title,item);
                        }
                    });
                    grid.setAdapter(gridAdapter);
                }
                MyListView list=helper.getView(R.id.list);
                if (item.lists!=null && item.lists.size()>0){
                    List<String> grids=item.lists;
                    MyListAdapter<String> adapter=new MyListAdapter<>(MyListActivity.this,grids,R.layout.item_grid);
                    adapter.setOnCallBackData(new MyListAdapter.OnCallBackData<String>() {
                        @Override
                        public void convertView(BaseViewHolder helper, String item) {
                            helper.setText(R.id.title,item);
                        }
                    });
                    list.setAdapter(adapter);
                }

            }
        });
        listview.setAdapter(listadapter);
    }


}
