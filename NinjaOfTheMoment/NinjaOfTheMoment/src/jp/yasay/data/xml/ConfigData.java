package jp.yasay.data.xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Xml;

// TODO データ形式はあとで合わせます
public class ConfigData {
	
	public static final int CHARACTER_WAIT = 0;
	public static final int CHARACTER_ATTACK = 1;
	public static final int CHARACTER_LOSS = 2;
	public static final int CHARACTER_WIN = 3;

	public static final int DEPTH_CHARACTERS = 2;
	public static final int DEPTH_CHARACTER = 3;

	private static final Map<String, ConfigData> cache = new HashMap<String, ConfigData>(); 
	
	public String mName;
	public String mResourceName;
	public List<Character> mCharacters;
	
	public static final class Character{
		public int mCharacterType = CHARACTER_WAIT;
		public Point mPosition;
		public Rect mDispArea;
	}
	
	public static final ConfigData read(final String xmlFile) throws XmlPullParserException, IOException{

		ConfigData ret = null;

		if (cache.containsKey(xmlFile)){
			ret = cache.get(xmlFile);
		} else {

			// TODO とりあえず、キャッシュ。
			final XmlPullParser parser = Xml.newPullParser();
			
			parser.setInput(new FileInputStream(xmlFile), "UTF-8");

			for(int e = parser.getEventType(); e != XmlPullParser.END_DOCUMENT; e = parser.next()){

				switch (e){
				
				case XmlPullParser.START_TAG:
				
					if (parser.getDepth() == DEPTH_CHARACTERS){
						setCharactersData(parser, ret);
					} else if (parser.getDepth() == DEPTH_CHARACTER){
						setCharacterData(parser, ret);
					}

					break;
					
				case XmlPullParser.TEXT:
					// TODO ここにはこない exception
					break;
					
				case XmlPullParser.END_TAG:
					// TODO ここにはこない exception
					break;
				}
			}
			
			cache.put(xmlFile, ret);
		}

		return ret;
	}
	
	private static final void setCharactersData(XmlPullParser parser, ConfigData data) throws XmlPullParserException, IOException{		

		for(int e = parser.getEventType(); e != XmlPullParser.END_DOCUMENT; e = parser.next()){

			switch (e){
			
			case XmlPullParser.START_TAG:

				if ("Name".equals(parser.getName())){
					data.mName = parser.getText();
				} else if ("Filename".equals(parser.getName())){
					data.mResourceName = parser.getText();
					return;
				}

				break;
			}
		}
	}
	
	private static final void setCharacterData(XmlPullParser parser, ConfigData data) throws XmlPullParserException, IOException{		

		Character ret = new Character();
		
		ret.mCharacterType = getCharacterType(parser.getName());
		
		for(int e = parser.getEventType(); e != XmlPullParser.END_DOCUMENT; e = parser.next()){

			switch (e){
			
			case XmlPullParser.START_TAG:

				if ("Position".equals(parser.getName())){

					parser.next();

					// TODO チェック。
					int x = Integer.parseInt(parser.getText());

					parser.next();

					// TODO チェック。
					int y = Integer.parseInt(parser.getText());
					
					ret.mPosition = new Point(x, y);
					
				} else if ("Location".equals(parser.getName())) {

					ret.mDispArea = new Rect();					
					parser.next();

					// TODO チェック。
					int left = Integer.parseInt(parser.getText());

					parser.next();

					// TODO チェック。
					int top = Integer.parseInt(parser.getText());
					
					ret.mDispArea.left = left;
					ret.mDispArea.top = top;
					
				} else if ("Size".equals(parser.getName())){
					
					ret.mDispArea = new Rect();					
					parser.next();

					// TODO チェック。
					int right = Integer.parseInt(parser.getText()) + ret.mDispArea.left;

					parser.next();

					// TODO チェック。
					int bottom = Integer.parseInt(parser.getText()) + ret.mDispArea.bottom;
					
					ret.mDispArea.right = right;
					ret.mDispArea.bottom = bottom;
	
					// TODO ここで追加。					
					data.mCharacters.add(ret);
				}

				break;
			}
		}
	}
	
	private static final int getCharacterType(String tag){

		if ("Wait".equals(tag)){
			return CHARACTER_WAIT;
		} else if ("Loss".equals(tag)){
			return CHARACTER_LOSS;
		} else if ("Attack".equals(tag)){
			return CHARACTER_ATTACK;
		} else if ("Win".equals(tag)){
			return CHARACTER_WIN;
		} else {
			throw new IllegalArgumentException();
		}
	}
}