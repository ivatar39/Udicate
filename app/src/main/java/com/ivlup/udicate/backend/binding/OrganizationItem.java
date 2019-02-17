package com.ivlup.udicate.backend.binding;

import android.support.annotation.NonNull;
import android.view.View;

import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.objects.Organization;
import com.ivlup.udicate.databinding.OrganizationItemBinding;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.databinding.BindableItem;

public class OrganizationItem extends BindableItem<OrganizationItemBinding> {
private Organization organization;

    public OrganizationItem(Organization organization) {
        this.organization = organization;
    }

    @Override
    public void bind(@NonNull final OrganizationItemBinding viewBinding, int position) {
        viewBinding.tvOrganizationName.setText(organization.getName());
        viewBinding.tvOrganizationAddress.setText(organization.getAddress());

        Picasso.get().load(organization.getLogo()).into(viewBinding.ivOrganizationLogo);

        viewBinding.iv1OrganizationType.setVisibility(View.VISIBLE);
        viewBinding.iv2OrganizationType.setVisibility(View.VISIBLE);
        viewBinding.iv3OrganizationType.setVisibility(View.VISIBLE);

        switch (organization.getType().length) {
            case 1:
                Picasso.get().load(organization.getType()[0]).into(viewBinding.iv1OrganizationType);
                viewBinding.iv2OrganizationType.setVisibility(View.GONE);
                viewBinding.iv3OrganizationType.setVisibility(View.GONE);
                break;
            case 2:
                Picasso.get().load(organization.getType()[0]).into(viewBinding.iv1OrganizationType);
                Picasso.get().load(organization.getType()[1]).into(viewBinding.iv2OrganizationType);
                viewBinding.iv3OrganizationType.setVisibility(View.GONE);
                break;
            case 3:
                Picasso.get().load(organization.getType()[0]).into(viewBinding.iv1OrganizationType);
                Picasso.get().load(organization.getType()[1]).into(viewBinding.iv2OrganizationType);
                Picasso.get().load(organization.getType()[2]).into(viewBinding.iv3OrganizationType);
                break;
        }
    }

    @Override
    public int getLayout() {
        return R.layout.organization_item;
    }

}
