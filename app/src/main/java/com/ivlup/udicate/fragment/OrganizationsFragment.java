package com.ivlup.udicate.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.Temp;
import com.ivlup.udicate.backend.binding.LessonItem;
import com.ivlup.udicate.backend.binding.OrganizationItem;
import com.ivlup.udicate.backend.objects.Lesson;
import com.ivlup.udicate.backend.objects.Organization;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class OrganizationsFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<Organization> organizations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.organizations_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.organizations_recycler);

        fetchOrganizations();
        fetchAdapter();
    }

    private void fetchOrganizations() {

        organizations = new ArrayList<>();
        organizations.clear();
        int type[];
        Organization organization;

        type = new int[]{R.drawable.atomic, R.drawable.division};
        organization = new Organization("Академия Гениев", "ул. Ленинский проспект, 18","http://edurobots.ru/wp-content/uploads/2018/03/agenio.png", type);
        organizations.add(organization);

        type = new int[]{R.drawable.atomic,R.drawable.chip};
        organization = new Organization("Робит", "ул. Ленинский проспект, 18","http://edurobots.ru/wp-content/uploads/2015/11/robit-logo-horisontal-rus.jpg", type);
        organizations.add(organization);

        type = new int[]{R.drawable.language};
        organization = new Organization("IT школа Samsung", "ул. Дружбы","http://www.239.ru/userfiles/image/logo(1).jpg", type);
        organizations.add(organization);
    }

    private void fetchAdapter() {
        GroupAdapter adapter = new GroupAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter.clear();

        ArrayList<Item> items = new ArrayList<>();


        for (Organization organization : organizations) {
            items.add(new OrganizationItem(organization));
        }
        adapter.addAll(items);
    }

}
