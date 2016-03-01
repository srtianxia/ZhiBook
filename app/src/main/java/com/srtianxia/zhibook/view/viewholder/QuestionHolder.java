package com.srtianxia.zhibook.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.Question;
import com.srtianxia.zhibook.utils.ui.CircleImageView;

/**
 * Created by srtianxia on 2016/2/6.
 */
public class QuestionHolder extends RecyclerView.ViewHolder{
    private TextView tv_question_author;
    private TextView tv_question_title;
    private TextView tv_question_content;
    private TextView tv_question_answerCount;
//    private ImageView img_question_author_head;
    private CircleImageView simpleDraweeView;

//    private static final int IO_BUFFER_SIZE = 8 * 1024;
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            BufferedInputStream in = new BufferedInputStream((InputStream) msg.obj,IO_BUFFER_SIZE);
//            Bitmap bitmap = BitmapFactory.decodeStream(in);
//            if(bitmap == null){
//                Log.d("123","bitmap = null");
//            }
//            img_question_author_head.setImageBitmap(bitmap);
//        }
//    };
    public QuestionHolder(View itemView){
        super(itemView);
        tv_question_author = (TextView) itemView.findViewById(R.id.tv_question_author);
        tv_question_title = (TextView) itemView.findViewById(R.id.tv_question_title);
        tv_question_content = (TextView) itemView.findViewById(R.id.tv_question_content);
        tv_question_answerCount = (TextView) itemView.findViewById(R.id.tv_question_answerCount);
//        img_question_author_head = (ImageView) itemView.findViewById(R.id.img_question_author_head);
        simpleDraweeView = (CircleImageView) itemView.findViewById(R.id.img_question_author_head);
    }

    public void bindData(Question question,int position){
//        if (question.getAuthorHead()!=null) {
//            OkHttpUtils.asyGet(question.getAuthorHead(), new OkHttpUtilsCallback() {
//                @Override
//                public void onResponse(Response response, String status) throws IOException {
//                    Message message = new Message();
//                    message.obj = response.body().byteStream();
//                    handler.sendMessage(message);
//                }
//            });
//        }
        if (question.getAuthorHead()!=null){
            Glide.with(itemView.getContext())
                    .load(question.getAuthorHead())
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .crossFade()
                    .into(simpleDraweeView);
        }
        tv_question_author.setText(question.getAuthorName()+" 提出的问题");
        tv_question_title.setText(question.getTitle());
        tv_question_content.setText(question.getContent());
        tv_question_answerCount.setText(""+question.getAnswerCount());
//        img_question_author_head.setImageBitmap(question.get);
    }
}
