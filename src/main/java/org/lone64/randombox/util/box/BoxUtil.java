package org.lone64.randombox.util.box;

import org.lone64.randombox.RandomBox;
import org.lone64.randombox.object.repo.BoxRepo;

public class BoxUtil {

    public static BoxRepo load() {
        return RandomBox.get().getBoxRepo();
    }

}
