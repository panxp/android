package basketball.com.sports;


import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import basketball.com.sports.model.Video;

public class VideoAdapter extends BaseAdapter {

    private List<Video> videos; //数据

    private int resource;    //item的布局
    private Context context;
    private LayoutInflater inflator;

    private TextView titleTextView;
    private TextView lengthTextView;
    private ImageView imgImageView;
    private String Url;

    /**
     * @param context  mainActivity
     * @param videos   显示的数据
     * @param resource 一个Item的布局
     */
    public VideoAdapter(Context context, List<Video> videos, int resource) {
        this.context = context;
        this.videos = videos;
        this.resource = resource;
    }

    /*
     * 获得数据总数
     * */
    @Override
    public int getCount() {
        return videos.size();
    }

    /*
     * 根据索引为position的数据
     * */
    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }

    /*
     * 根据索引值获得Item的Id
     * */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     *通过索引值position将数据映射到视图
     *convertView具有缓存功能，在第一页时为null，在第二第三....页时不为null
     * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(resource, null);
            titleTextView = (TextView) convertView.findViewById(R.id.title);    //为了减少开销，则只在第一页时调用findViewById
            lengthTextView = (TextView) convertView.findViewById(R.id.length);
            imgImageView = (ImageView) convertView.findViewById(R.id.img);
        }
        Video video = videos.get(position);


        titleTextView.setText(video.getTitle());
        Picasso.with(context).load(video.getCover()).into(imgImageView);
        lengthTextView.setText(video.getLength());


        return convertView;
    }
}

