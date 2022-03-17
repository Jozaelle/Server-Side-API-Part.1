package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;

import javax.servlet.Registration;
import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDao dao;

    public AuctionController() {
        this.dao = new MemoryAuctionDao();
    }

    @RequestMapping( path = "", method = RequestMethod.GET)
    public List<Auction> list(@RequestParam(defaultValue = "") String title_like,@RequestParam(defaultValue = "0") double currentBid_lte) {


        if (currentBid_lte > 0 && title_like.equals((""))) {
            return dao.searchByPrice(currentBid_lte);

        } else if (!title_like.equals("") && currentBid_lte==0) {
            return dao.searchByTitle(title_like);
        }else if(title_like.equals("") && currentBid_lte == 0) {
            return dao.list();
        }
        else {
            return dao.searchByTitleAndPrice(title_like,currentBid_lte);
        }

    }




    //curly brackets signifies that it is a placeholder. id is a placeholder for any number
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Auction get(@PathVariable int id){
        return dao.get(id);

    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Auction create(@RequestBody Auction location){
        return dao.create(location);

    }
}




