package com.blibli.future.utility;


import com.blibli.future.model.Catering;
import com.blibli.future.repository.CateringRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchServiceImpl implements SearchService{

    @Autowired
    CateringRepository cateringRepository;

    @Override
    public List<Catering> search(String searchTerm) {
        return null;
    }
}
