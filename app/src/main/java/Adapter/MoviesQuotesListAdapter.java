package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moviesquotesjprtechnosofttroughfirebase.DashBoardMainActivity;
import com.moviesquotesjprtechnosofttroughfirebase.R;
import com.moviesquotesjprtechnosofttroughfirebase.SharedDataActivity;

import java.util.List;

import model.MoviesQuotes;

public class MoviesQuotesListAdapter extends RecyclerView.Adapter<MoviesQuotesListAdapter.MyViewHolder> {

    private Context context;
    private List<MoviesQuotes>moviesQuotesList;
    private MoviesQuotesListInterface moviesQuotesListInterface;///


    public MoviesQuotesListAdapter(Context context, List<MoviesQuotes>moviesQuotesList1){
        this.context = context;
        this.moviesQuotesList = moviesQuotesList1;
    }

  /*  public MoviesQuotesListAdapter(List<MoviesQuotes> moviesQuotesList) {
        this.moviesQuotesList=moviesQuotesList;

    } */

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_movies_quotes_item, viewGroup ,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {

         final MoviesQuotes moviesQuotes =moviesQuotesList.get(position);

         myViewHolder.quoteTxt.setText(moviesQuotes.getQuoteTxt());
         myViewHolder.writerName.setText(moviesQuotes.getWriterName());


         myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (moviesQuotesListInterface != null) {
                     moviesQuotesListInterface.moviesQuotesListItem(myViewHolder.getAdapterPosition());
                     //moviesQuotesListInterface.moviesQuotesListItem(moviesQuotesList.get(i).getWriterName());
                 }


             }
         });




    }
    public void setmoviesQuotesListInterface(MoviesQuotesListInterface moviesQuotesListInterface) {    ///
        this.moviesQuotesListInterface = moviesQuotesListInterface;                                 ///
    }
    public interface MoviesQuotesListInterface {       ///
        public void moviesQuotesListItem(int position);

    }


    @Override
    public int getItemCount() {
        if ( moviesQuotesList!= null) {
            return moviesQuotesList.size();
        } else {
            return 10;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView favoriteIMG,profile_image;
        private ImageView shareIMG;
        private TextView quoteTxt;
        private TextView writerName;
        private RelativeLayout mainView;
        //private RelativeLayout expandedView;
        private ProgressBar imageLoader;
        private View lineView;
        private CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            favoriteIMG = itemView.findViewById(R.id.favoriteIMG);
            shareIMG = itemView.findViewById(R.id.shareIMG);
            quoteTxt = itemView.findViewById(R.id.quoteText);
            mainView = itemView.findViewById(R.id.mainView);
            lineView = itemView.findViewById(R.id.lineView);
           // expandedView = itemView.findViewById(R.id.expandedView);
            writerName = itemView.findViewById(R.id.writerName);
            profile_image=itemView.findViewById(R.id.profile_image);
            cardView = itemView.findViewById(R.id.cardview);

        }
    }
}
