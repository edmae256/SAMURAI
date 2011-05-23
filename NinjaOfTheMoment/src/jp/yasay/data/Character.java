/**
 *
 */
package jp.yasay.data;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.ImageView;



/**
 * @author Owner
 *
 */
public class Character implements Closeable {
	/**
	 * 空のイメージデータ配列
	 */
	public final static ImageData[] EmptyImageDatas = new ImageData[0];

	private Map<CharacterState,ImageData[]> drawMaps = new HashMap<CharacterState,ImageData[]>(8);

	private CharacterState state;
	private String name;
	private int count;


	private ImageView image;

	/**
	 * 状態のリセット
	 */
	public void reset(){
		this.state = CharacterState.Wait;
		this.count = 0;
	}

	public void Load(Context context){
		if(this.name != null && 0 < this.name.length()){
			image = new ImageView(context);
			//TODO: 名前から取得したい場合、どこに配置したものどう参照すればよいのだろうか…
			image.setImageBitmap(BitmapFactory.decodeFile(this.name));
		}
	}

	/**
	 * 初期化
	 */
	public Character(){
		reset();
	}

	/*** java properties  start ***/
	public ImageData[] getImageData(CharacterState state){
		if(drawMaps.containsKey(state)) return drawMaps.get(state);
		return EmptyImageDatas;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setWait(ImageData[] value){
		drawMaps.put(CharacterState.Wait,value);
	}
	public ImageData[] getWait(){
		return getImageData(CharacterState.Wait);
	}
	public void setWin(ImageData[] value){
		drawMaps.put(CharacterState.Win, value);
	}
	public ImageData[] getWin(){
		return getImageData(CharacterState.Win);
	}
	public void setLoss(ImageData[] value){
		drawMaps.put(CharacterState.Loss,value);
	}
	public ImageData[] getLoss(){
		return getImageData(CharacterState.Loss);
	}
	public void setAttack(ImageData[] value){
		drawMaps.put(CharacterState.Attack, value);
	}
	public ImageData[] getAttack(){
		return getImageData(CharacterState.Attack);
	}
	public CharacterState getState(){ return this.state; }
	public void setState(CharacterState st){
		this.state = st;
		if(!drawMaps.containsKey(st)) drawMaps.put(st,EmptyImageDatas);
	}
	/*** java properties  end  ***/

	/* (非 Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

}
