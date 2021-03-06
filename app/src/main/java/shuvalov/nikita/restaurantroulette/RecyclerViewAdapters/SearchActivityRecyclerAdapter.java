package shuvalov.nikita.restaurantroulette.RecyclerViewAdapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import shuvalov.nikita.restaurantroulette.Activities.DateNightSearchActivity;
import shuvalov.nikita.restaurantroulette.Activities.DetailActivity;
import shuvalov.nikita.restaurantroulette.Activities.SearchActivity;
import shuvalov.nikita.restaurantroulette.DateNightHelper;
import shuvalov.nikita.restaurantroulette.OurAppConstants;
import shuvalov.nikita.restaurantroulette.PicassoImageManager;
import shuvalov.nikita.restaurantroulette.R;
import shuvalov.nikita.restaurantroulette.RestaurantSearchHelper;
import shuvalov.nikita.restaurantroulette.YelpResources.YelpObjects.Business;

/**
 * Created by NikitaShuvalov on 12/1/16.
 */

/**
 * Created by NikitaShuvalov on 11/24/16.
 */

public class SearchActivityRecyclerAdapter extends RecyclerView.Adapter<SearchResultViewHolder> {
    List<Business> mBusinessList;

    public SearchActivityRecyclerAdapter(List<Business> businessList) {
        mBusinessList = businessList;
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_search_form, null);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchResultViewHolder holder, final int position) {
        holder.bindDataToView(mBusinessList.get(position));
        holder.mHolderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.mNameView.getContext(), DetailActivity.class);
                intent.putExtra(OurAppConstants.BUSINESS_POSITION_INTENT_KEY,position);
                intent.putExtra(OurAppConstants.ORIGIN,OurAppConstants.SEARCH_ORIGIN);
                holder.mHolderLayout.getContext().startActivity(intent);
            }
        });
        if(holder.itemView.getContext().getClass().equals(DateNightSearchActivity.class)){
            holder.mAddButton.setVisibility(View.VISIBLE);
            holder.mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<Business> results =RestaurantSearchHelper.getInstance().getSearchResults();
                    Business selectedBusiness = results.get(position);
                    DateNightHelper.getInstance().addBusinessToItinerary(selectedBusiness);
                    ((Activity)view.getContext()).finish();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mBusinessList.size();
    }
    public void replaceList(List<Business> newSearchResult){
        mBusinessList.clear();
        mBusinessList.addAll(newSearchResult);
    }
}

class SearchResultViewHolder extends RecyclerView.ViewHolder{
    TextView mNameView, mDescView, mPrice;
    ImageView mPicture, mAddButton, mRemoveButton;
    RelativeLayout mHolderLayout;
    LinearLayout mStarRatingHolder;

    public SearchResultViewHolder(View itemView) {
        super(itemView);
        mNameView = (TextView)itemView.findViewById(R.id.business_name_view);
        mDescView = (TextView)itemView.findViewById(R.id.description_text_view);
        mPrice = (TextView)itemView.findViewById(R.id.price_view);
        mHolderLayout = (RelativeLayout)itemView.findViewById(R.id.holder_layout);
        mPicture = (ImageView)itemView.findViewById(R.id.picture);
        mStarRatingHolder = (LinearLayout)itemView.findViewById(R.id.star_rating_container);
        mAddButton = (ImageView)itemView.findViewById(R.id.add_button);
        mRemoveButton = (ImageView)itemView.findViewById(R.id.remove_button);
    }

    public void bindDataToView(Business business){
        mNameView.setText(business.getName());
        int distance = business.getDistance().intValue();
        String distanceText;

        //ToDo: Put into this snippet of code into a Utility class if we're going to be using it often?
        if (distance>1000){//Changes format to look like "2.4" km away, instead of "2400 meters away" for large distances.
            distanceText = (distance/100)/10.0f+" km away";
        }else {
            distanceText = distance+ " meters away";
        }

        mDescView.setText(distanceText); //ToDo: Can(Or Should) we change from metric (Meters/km) to Freedom Units(miles/feet)?
        mPrice.setText(business.getPrice());

        double rating = business.getRating();
        applyStarRating(rating);


        PicassoImageManager picassoImageManager = new PicassoImageManager(mPicture.getContext(), mPicture);
        picassoImageManager.setImageFromUrl(business.getImageUrl());
    }

    //Unnecessarily complicated method to set imageResource for stars.
    public void applyStarRating(double rating) {
        boolean halfStar = rating % 1 == 0.5;
        int lastFullStar = (int) rating;
        int usedImage = R.drawable.blank_star_rating;
        if (rating == 0) {
            for (int i = 0; i < 5; i++) {
                ImageView starImage = (ImageView) mStarRatingHolder.getChildAt(i);
                starImage.setImageResource(usedImage);
            }
        } else if (rating == 5) {
            for (int i = 0; i < 5; i++) {
                ImageView starImage = (ImageView) mStarRatingHolder.getChildAt(i);
                starImage.setImageResource(R.drawable.five_star_rating);
            }
        } else {

            switch (lastFullStar) {
                case 1:
                    usedImage = R.drawable.one_star_rating;
                    break;
                case 2:
                    usedImage = R.drawable.two_star_rating;
                    break;
                case 3:
                    usedImage = R.drawable.three_star_rating;
                    break;
                case 4:
                    usedImage = R.drawable.four_star_rating;
                    break;
            }
            for (int i = 0; i < lastFullStar; i++) {
                ImageView starImage = (ImageView) mStarRatingHolder.getChildAt(i);
                starImage.setImageResource(usedImage);
            }
            for (int i = 4; i >lastFullStar-1; i--) {
                ImageView starImage = (ImageView) mStarRatingHolder.getChildAt(i);
                starImage.setImageResource(R.drawable.blank_star_rating);
            }
            if(halfStar){
                ImageView starImage = (ImageView) mStarRatingHolder.getChildAt(lastFullStar);
                switch (lastFullStar){
                    case 1:
                        starImage.setImageResource(R.drawable.one_half_star_rating);
                        break;
                    case 2:
                        starImage.setImageResource(R.drawable.two_half_star_rating);
                        break;
                    case 3:
                        starImage.setImageResource(R.drawable.three_half_star_rating);
                        break;
                    case 4:
                        starImage.setImageResource(R.drawable.four_half_star_rating);
                        break;
                }
            }
        }

    }
}
