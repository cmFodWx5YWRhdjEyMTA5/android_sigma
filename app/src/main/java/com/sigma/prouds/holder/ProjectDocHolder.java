package com.sigma.prouds.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sigma.prouds.R;
import com.sigma.prouds.model.ProjectDocModel;

import org.w3c.dom.Text;

/**
 * Created by 1414 on 7/24/2017.
 */

public class ProjectDocHolder extends RecyclerView.ViewHolder
{
    private Context context;
    private ImageView ivDocType;
    private TextView tvDocName;
    private ImageView ivDocDownload;
    private TextView tvDocUploader;
    private TextView tvDocDate;
    private TextView tvDocDesc;

    public ProjectDocHolder(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        ivDocType = (ImageView) itemView.findViewById(R.id.iv_doc_type);
        tvDocName = (TextView) itemView.findViewById(R.id.tv_doc_name);
        ivDocDownload = (ImageView) itemView.findViewById(R.id.iv_doc_download);
        tvDocUploader = (TextView) itemView.findViewById(R.id.tv_doc_uploader);
        tvDocDate = (TextView) itemView.findViewById(R.id.tv_doc_date);
        tvDocDesc = (TextView) itemView.findViewById(R.id.tv_doc_desc);
    }

    public void bind(ProjectDocModel model)
    {
        tvDocName.setText(model.getDocName());
        tvDocUploader.setText(model.getUploadBy());
        tvDocDate.setText(model.getDateUpload());
        tvDocDesc.setText(model.getDocDesc());
    }
}