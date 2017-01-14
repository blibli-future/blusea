package com.blibli.future.utility;


import com.blibli.future.model.Catering;

import java.util.ArrayList;
import java.util.List;

public interface SearchService {

    public List<Catering> search(String searchTerm);
}
