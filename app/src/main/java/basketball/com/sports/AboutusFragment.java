package basketball.com.sports;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutusFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.tab03, container, false);
		TextView textView = (TextView)view.findViewById(R.id.aboutus);
		textView.setMovementMethod(ScrollingMovementMethod.getInstance());
		return view;
	}
}
