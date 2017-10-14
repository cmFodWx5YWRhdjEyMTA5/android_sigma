package com.sigma.prouds.holder;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.model.ProjectDocModel;

/**
 * Created by 1414 on 7/24/2017.
 */

public class ProjectDocHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private ImageView ivDocWord, ivDocExcel;
    private TextView tvDocName;
    private ImageView ivDocDownload;
    private TextView tvDocUploader;
    private TextView tvDocDate;
    private TextView tvDocDesc;
    private Typeface latoRegular;

    public ProjectDocHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        ivDocWord = (ImageView) itemView.findViewById(R.id.iv_doc_msword);
        ivDocExcel = (ImageView) itemView.findViewById(R.id.iv_doc_msexcel);
        tvDocName = (TextView) itemView.findViewById(R.id.tv_doc_name);
        ivDocDownload = (ImageView) itemView.findViewById(R.id.iv_doc_download);
        tvDocUploader = (TextView) itemView.findViewById(R.id.tv_doc_uploader);
        tvDocDate = (TextView) itemView.findViewById(R.id.tv_doc_date);
        tvDocDesc = (TextView) itemView.findViewById(R.id.tv_doc_desc);

        latoRegular = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_regular.ttf");
    }

    public void bind(ProjectDocModel model)
    {
        tvDocName.setText(model.getDocName());
        tvDocUploader.setText("uploaded by " + model.getUploadBy());
        tvDocDate.setText(" at " + model.getDateUpload());
        tvDocDesc.setText(model.getDocDesc());

        try
        {
            if (model.getDocName().toLowerCase().contains("doc") || model.getDocName().toLowerCase().contains("docx")) {
                ivDocWord.setVisibility(View.VISIBLE);
                ivDocExcel.setVisibility(View.GONE);
            }
            else  if (model.getDocName().toLowerCase().contains("xls") || model.getDocName().toLowerCase().contains("xlsx")) {
                ivDocWord.setVisibility(View.GONE);
                ivDocExcel.setVisibility(View.VISIBLE);
            }
            else {
                ivDocWord.setVisibility(View.GONE);
                ivDocExcel.setVisibility(View.GONE);
            }
        }
        catch (Exception e)
        {
            ivDocWord.setVisibility(View.GONE);
            ivDocExcel.setVisibility(View.GONE);
        }
        setTypeFace();
    }

    public void setTypeFace() {
        tvDocName.setTypeface(latoRegular);
        tvDocUploader.setTypeface(latoRegular);
        tvDocDate.setTypeface(latoRegular);
        tvDocDesc.setTypeface(latoRegular);
    }
}
