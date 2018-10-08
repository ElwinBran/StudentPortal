package com.github.elwinbran.studentportal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PortalGridAdapter extends RecyclerView.Adapter<PortalCellViewHolder>
{

    private final List<Portal> portals;
    private final MainActivity main;

    public PortalGridAdapter(List<Portal> portals, MainActivity main)
    {
        this.portals = portals;
        this.main = main;
    }

    @NonNull
    @Override
    public PortalCellViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_cell, viewGroup,
                false);
        return new PortalCellViewHolder(view, main);
    }

    @Override
    public void onBindViewHolder(@NonNull PortalCellViewHolder portalCellViewHolder, int i)
    {
        portalCellViewHolder.setPortal(this.portals.get(i));
    }

    @Override
    public int getItemCount()
    {
        return this.portals.size();
    }
}
