/**
 *
 */
package jp.yasay.data;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



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
	private int count;

	/**
	 * 状態のリセット
	 */
	public void reset(){
		this.state = CharacterState.Wait;
		this.count = 0;
	}

	/**
	 * 初期化
	 */
	public Character(){
		reset();
	}




	/**
	 * ステータス取得
	 * @return
	 */
	public CharacterState getState(){ return this.state; }
	/**
	 * ステータス変更
	 * @param st
	 */
	public void setState(CharacterState st){
		this.state = st;
		if(!drawMaps.containsKey(st)) drawMaps.put(st,EmptyImageDatas);
	}


	/* (非 Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

}
