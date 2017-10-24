package com.Hackathon6.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.Hackathon6.android.ImageLargeActivity;
import com.Hackathon6.android.R;
import com.bumptech.glide.Glide;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGBuilder;
import com.larvalabs.svgandroid.SVGParser;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by simple on 23/10/2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mImageUrlList;

    public ImageAdapter(Context context, List<String> imageUrlList) {
        mContext = context;
        mImageUrlList = imageUrlList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final String imageUrl = mImageUrlList.get(position);
        if (imageUrl.endsWith(".svg")){
            new HttpImageRequestTask().execute(holder.imageView,imageUrl);
        }else{
            Glide.with(mContext)
                    .load(mImageUrlList.get(position))
                    .into(holder.imageView);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ImageLargeActivity.class);
                intent.putExtra("imageUrl", imageUrl);
                mContext.startActivity(intent);
            }
        });

    }

    private class HttpImageRequestTask extends AsyncTask<Object, Void, Drawable> {

        private ImageView imageView;

        @Override
        protected Drawable doInBackground(Object... params) {
            try {
                imageView = (ImageView) params[0];

                final URL url = new URL((String) params[1]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
//                File imgFile = new File((String) params[1]);
//                FileInputStream fileInputStream = new FileInputStream(inputStream);
                SVG svg = new SVGBuilder()
                        .readFromInputStream(inputStream)
                        .build();

                Bitmap bitmap = Bitmap.createBitmap(200, 100, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                Picture pic = svg.getPicture();
                canvas.drawPicture(pic, new Rect(0, 0, 200, 100));

//                PictureDrawable drawable = svg.getDrawable();
//                Canvas canvas = new Canvas();
//                canvas.drawPicture(svg.getPicture());
//                drawable.draw(canvas);
                BitmapDrawable drawable= new BitmapDrawable(mContext.getResources(), bitmap);
                return drawable;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            if(drawable != null){
                imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                imageView.setImageDrawable(drawable);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mImageUrlList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
        }
    }
}