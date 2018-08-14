package ec.edu.ups.unesco.giiata.adacof20.ventanas.moduloUnoFonatorios;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ec.edu.ups.unesco.giiata.adacof20.R;
import ec.edu.ups.unesco.giiata.adacof20.ventanas.ChildFragment;
import ec.edu.ups.unesco.giiata.adacof20.ventanas.PageAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMenuFonatorios.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMenuFonatorios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMenuFonatorios extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;





    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabChats;
    TabItem tabStatus;
    TabItem tabCalls;
    private OnFragmentInteractionListener mListener;

    public FragmentMenuFonatorios() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMenuConciencia.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMenuFonatorios newInstance(String param1, String param2) {
        FragmentMenuFonatorios fragment = new FragmentMenuFonatorios();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View root = inflater.inflate(R.layout.fragment_menu_fonatorios, container, false);


        FragmentTabHost tabHost = (FragmentTabHost) root.findViewById(android.R.id.tabhost);
        /** Important: Must use child FragmentManager. */
        tabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

        Bundle arg1 = new Bundle();
        arg1.putInt(ChildFragmentFonatorios.POSITION_KEY, 1);
        tabHost.addTab(tabHost.newTabSpec("ChildTag1").setIndicator("SOPLO"),
                FragmentMenuFonatoriosSoplo.class, arg1);

        Bundle arg2 = new Bundle();
        arg2.putInt(ChildFragmentFonatorios.POSITION_KEY, 2);
        tabHost.addTab(tabHost.newTabSpec("ChildTag2").setIndicator("RESPIRACION"),
                FragmentMenuFonatoriosRespiracion.class, arg2);


        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
