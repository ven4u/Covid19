package com.android.covid19.adaptor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.android.covid19.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
	private static ClickListener clickListener;
	//private List<NatureModel> objectList;
	private ArrayList<BarDataSet> list;
	private LayoutInflater inflater;
	private int [] colorArray = {Color.RED,Color.GREEN,Color.CYAN};
	private String [] StackLables = {"Deaths","Recovered","Active"};
	final String TAG = this.getClass().getName();


	public MyAdapter(Context context, ArrayList<BarDataSet> list) {
		inflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.list_item, parent, false);
		MyViewHolder holder = new MyViewHolder(view);
		return holder;
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {

		//for (BarDataSet barDataSet : list) {
			//Log.d(TAG, String.valueOf(barDataSet.getStackSize()));
			BarDataSet barDataSet = list.get(position);
			barDataSet.setColors(colorArray);
			barDataSet.setStackLabels(StackLables);
			barDataSet.setBarBorderWidth(1.f);
			barDataSet.setBarShadowColor(Color.GRAY);
			barDataSet.isHighlightEnabled();
			BarData barData = new BarData(barDataSet);

			barData.setBarWidth(0.60f);
			BarChart current = holder.barChart;
			current.setData(barData);
			current.setFitBars(true);
			Description des = new Description();
			des.setText("");
			current.setDescription(des);
			current.animateXY(1000, 1000);

			holder.setData(current, position);

		//}
		//holder.setListeners();
	}

	public void setOnItemClickListener(ClickListener clickListener) {
		MyAdapter.clickListener = clickListener;
	}

	public interface ClickListener {
		void onItemClick(int position, View v);
		void onItemLongClick(int position, View v);
	}

	class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

		//private TextView title;
		//private TextView discription;
		//private ImageView imgThumb, imgDelete, imgCopy;
		private BarChart barChart;
		private int position;
		//private NatureModel currentObject;
		BarChart currentList;



		public MyViewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
			itemView.setOnLongClickListener(this);
			barChart = (BarChart) itemView.findViewById(R.id.stackbarchart);

			//title = (TextView) itemView.findViewById(R.id.tvTitle);
			//discription = (TextView) itemView.findViewById(R.id.tvDiscription);

			//imgThumb    = (ImageView) itemView.findViewById(R.id.img_thumb);
			//imgDelete   = (ImageView) itemView.findViewById(R.id.img_delete);
			//imgCopy = (ImageView) itemView.findViewById(R.id.img_copy);
		}

		public void setData(BarChart current, int position) {
			this.barChart.setData(current.getBarData());
			//this.discription.setText(current.getActionDiscription());
			//this.imgThumb.setImageResource(currentObject.getImageID());
			this.position = position;
			this.currentList = current;
		}

		@Override
		public void onClick(View v) {
			if (clickListener != null) {
				clickListener.onItemClick(getAdapterPosition(), v);
			}
		}
		@Override
		public boolean onLongClick (View v){
			clickListener.onItemLongClick(getAdapterPosition(), v);
			return false;
		}
	}
}



		/**public void setListeners() {
			imgDelete.setOnClickListener(MyViewHolder.this);
			imgCopy.setOnClickListener(MyViewHolder.this);
			imgThumb.setOnClickListener(MyViewHolder.this);
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.img_delete:
					removeItem(position);
					break;

				case R.id.img_copy:
					addItem(position, currentObject);
					break;
			}
		}
	}

	public void removeItem(int position) {
		objectList.remove(position);
		notifyItemRemoved(position);
		notifyItemRangeChanged(position, objectList.size());
//		notifyDataSetChanged();
	}

	public void addItem(int position, NatureModel currentObject) {
		objectList.add(position, currentObject);
		notifyItemInserted(position);
		notifyItemRangeChanged(position, objectList.size());
//		notifyDataSetChanged();
	}
	**/
