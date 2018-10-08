package com.github.elwinbran.studentportal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class PortalCellViewHolder extends RecyclerView.ViewHolder
{

    final private Button button;
    final public View view;
    final private MainActivity main;

    public PortalCellViewHolder(@NonNull View itemView, MainActivity main)
    {
        super(itemView);
        button = itemView.findViewById(R.id.button);
        view = itemView;
        this.main = main;
    }

    public void setPortal(final Portal portal)
    {
        button.setText(portal.getTitle());
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                main.browseUrl(portal.getUrl());
            }
        });
    }
}
