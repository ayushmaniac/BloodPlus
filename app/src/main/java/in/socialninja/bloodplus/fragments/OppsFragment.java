package in.socialninja.bloodplus.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import in.socialninja.bloodplus.R;

/**
 * Created by Unique on 06-03-2018.
 */

public class OppsFragment extends Fragment {
    View view;
    ProgressDialog mProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_oops, container, false);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Fade explode = new Fade();
            explode.setDuration(800);
            setEnterTransition(explode);
        }


        ImageView imageView = (ImageView) view.findViewById(R.id.heart);
        imageView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.pulse));


        initComponent(view);
        return view;

    }

    private void initComponent(View view) {

    }

    public void transact(Fragment fragment) {
        FragmentTransaction transf = getFragmentManager().beginTransaction();
        transf.replace(R.id.root_frame, fragment);
        transf.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transf.addToBackStack(null);
        transf.commit();
    }

}
