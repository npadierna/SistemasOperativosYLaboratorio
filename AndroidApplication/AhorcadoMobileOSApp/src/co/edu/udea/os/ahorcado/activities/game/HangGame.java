package co.edu.udea.os.ahorcado.activities.game;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import co.edu.udea.os.ahorcado.R;
import co.edu.udea.os.ahorcado.persistence.entity.Category;
import co.edu.udea.os.ahorcado.persistence.entity.CategoryWords;
import co.edu.udea.os.ahorcado.persistence.entity.Player;

/**
 * 
 * @author Andersson Garc&iacute;a Sotelo
 * @author Neiber Padierna P&eacute;rez
 */
final class HangGame implements Parcelable {

	public static final int STARTING = 0;
	public static final int HEAD = 1;
	public static final int CHEST = 2;
	public static final int LEFT_ARM = 3;
	public static final int RIGHT_ARM = 4;
	public static final int LEFT_LEG = 5;
	public static final int RIGHT_LEG = 6;

	private static final List<Integer> IMAGES_STATE;
	static {
		IMAGES_STATE = new ArrayList<Integer>();
		IMAGES_STATE.add(HangGame.STARTING,
				Integer.valueOf(R.drawable.ic_hanging_state_starting));
		IMAGES_STATE.add(HangGame.HEAD,
				Integer.valueOf(R.drawable.ic_hanging_state_head));
		IMAGES_STATE.add(HangGame.CHEST,
				Integer.valueOf(R.drawable.ic_hanging_state_chest));
		IMAGES_STATE.add(HangGame.LEFT_ARM,
				Integer.valueOf(R.drawable.ic_hanging_state_left_arm));
		IMAGES_STATE.add(HangGame.RIGHT_ARM,
				Integer.valueOf(R.drawable.ic_hanging_state_right_arm));
		IMAGES_STATE.add(HangGame.LEFT_LEG,
				Integer.valueOf(R.drawable.ic_hanging_state_left_leg));
		IMAGES_STATE.add(HangGame.RIGHT_LEG,
				Integer.valueOf(R.drawable.ic_hanging_state_right_leg));
	}

	private int punishment;
	private int score;
	private int state;

	private Category category;
	private CategoryWords categoryWords;
	private Player player;

	public HangGame(Category category, Player player,
			CategoryWords categoryWords, int initalScore, int punishment) {
		super();

		this.setPunishment(punishment);
		this.setScore(initalScore);
		this.setState(HangGame.STARTING);

		this.setCategory(category);
		this.setPlayer(player);
		this.setCategoryWords(categoryWords);
	}

	public HangGame(Parcel parcel) {
		this.setScore(parcel.readInt());
		this.setState(parcel.readInt());
		this.setCategory((Category) parcel.readParcelable(Category.class
				.getClassLoader()));
		this.setCategoryWords((CategoryWords) parcel
				.readParcelable(CategoryWords.class.getClassLoader()));
		this.setPlayer((Player) parcel.readParcelable(Category.class
				.getClassLoader()));
	}

	public int getPunishment() {

		return (this.punishment);
	}

	public void setPunishment(int punishment) {
		this.punishment = punishment;
	}

	public int getScore() {

		return (this.score);
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getState() {

		return (this.state);
	}

	public void setState(int state) {
		this.state = state;
	}

	public Category getCategory() {

		return (this.category);
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CategoryWords getCategoryWords() {

		return (this.categoryWords);
	}

	public void setCategoryWords(CategoryWords word) {
		this.categoryWords = word;
	}

	public Player getPlayer() {

		return (this.player);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override()
	public int describeContents() {

		return (0);
	}

	@Override()
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.getScore());
		dest.writeInt(this.getState());
		dest.writeParcelable(this.getCategory(), 0);
		dest.writeParcelable(this.getCategoryWords(), 0);
		dest.writeParcelable(this.getPlayer(), 0);
	}

	public int doPunishment() {
		if (this.changeHangingState(this.getState())) {
			this.setScore(this.getScore() - this.getPunishment());

			return (this.getState());
		}

		return (-1);
	}

	public boolean isLost() {

		return (this.getState() == HangGame.RIGHT_LEG);
	}

	public int obtainDrawableByState(int state) {

		return (HangGame.IMAGES_STATE.get(state).intValue());
	}

	private boolean changeHangingState(int currentState) {
		switch (currentState) {
		case (HangGame.STARTING):
			this.setState(HangGame.HEAD);
			break;

		case (HangGame.HEAD):
			this.setState(HangGame.CHEST);
			break;

		case (HangGame.CHEST):
			this.setState(HangGame.LEFT_ARM);
			break;

		case (HangGame.LEFT_ARM):
			this.setState(HangGame.RIGHT_ARM);
			break;

		case (HangGame.RIGHT_ARM):
			this.setState(HangGame.LEFT_LEG);
			break;

		case (HangGame.LEFT_LEG):
			this.setState(HangGame.RIGHT_LEG);
			break;

		default:
			return (false);
		}

		return (true);
	}

	public static final Parcelable.Creator<HangGame> CREATOR = new Parcelable.Creator<HangGame>() {

		@Override()
		public HangGame createFromParcel(Parcel source) {

			return (new HangGame(source));
		}

		@Override()
		public HangGame[] newArray(int size) {

			return (new HangGame[size]);
		}
	};
}