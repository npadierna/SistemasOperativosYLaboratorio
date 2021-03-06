package co.edu.udea.os.ahorcado.activities.bestrecords;

import java.text.SimpleDateFormat;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.persistence.entity.Record;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 * @author Santiago G&oacute;mez Piedrah&iacute;ta
 */
final class RecordsArrayAdapter extends ArrayAdapter<Record> {

	private int resource;
	private Activity activity;
	private List<Record> records;

	public RecordsArrayAdapter(Activity activity, int resource,
			List<Record> records) {
		super(activity.getApplicationContext(), resource, records);

		this.setResource(resource);
		this.setActivity(activity);
		this.setRecords(records);
	}

	@Override()
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		RecordViewHolder recordViewHolder;

		if (view == null) {
			LayoutInflater layoutInflater = this.getActivity()
					.getLayoutInflater();

			view = layoutInflater.inflate(this.getResource(), null);

			recordViewHolder = new RecordViewHolder();
			recordViewHolder.setCategoryNameTextView((TextView) view
					.findViewById(R.id.category_name_text_view));
			recordViewHolder.setDateTextView((TextView) view
					.findViewById(R.id.record_date_text_view));
			recordViewHolder.setPlayerUserNameTextView((TextView) view
					.findViewById(R.id.player_user_name_text_view));
			recordViewHolder.setRecordTextView((TextView) view
					.findViewById(R.id.record_text_view));
			recordViewHolder.setWordTextView((TextView) view
					.findViewById(R.id.record_word_text_view));

			view.setTag(recordViewHolder);
		} else {
			recordViewHolder = (RecordViewHolder) view.getTag();
		}

		this.fillRecordDetails(position, recordViewHolder);

		return (view);
	}

	public Activity getActivity() {

		return (this.activity);
	}

	private void setActivity(Activity ownerActivity) {
		this.activity = ownerActivity;
	}

	public int getResource() {

		return (this.resource);
	}

	private void setResource(int resource) {
		this.resource = resource;
	}

	public List<Record> getRecords() {

		return (this.records);
	}

	private void setRecords(List<Record> records) {
		this.records = records;
	}

	@SuppressLint("SimpleDateFormat")
	private void fillRecordDetails(int position,
			RecordViewHolder recordViewHolder) {
		Record record = this.getRecords().get(position);
		StringBuilder string = new StringBuilder();
		TextView textView = null;

		textView = recordViewHolder.getCategoryNameTextView();
		textView.setText(record.getRecordPK().getCategory());

		textView = recordViewHolder.getDateTextView();
		string.append(
				this.getActivity().getResources().getString(R.string.date_text))
				.append(" ");
		textView.setText(string.append(new SimpleDateFormat("dd-MM-yyy")
				.format(record.getDate())));

		textView = recordViewHolder.getPlayerUserNameTextView();
		string.delete(0, string.length());
		string.append(
				this.getActivity().getResources()
						.getString(R.string.user_name_text)).append(" ");
		textView.setText(string.append(record.getRecordPK().getUserName())
				.toString());

		textView = recordViewHolder.getRecordTextView();
		string.delete(0, string.length());
		string.append(
				this.getActivity().getResources()
						.getString(R.string.record_text)).append(" ");
		textView.setText(string.append(Integer.toString(record.getPoints()))
				.toString());

		textView = recordViewHolder.getWordTextView();
		string.delete(0, string.length());
		string.append(
				this.getActivity().getResources().getString(R.string.word_text))
				.append(" ");
		textView.setText(string.append(record.getRecordPK().getWord()));
	}
}