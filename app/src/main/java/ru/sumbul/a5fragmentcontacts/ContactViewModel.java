package ru.sumbul.a5fragmentcontacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class ContactViewModel extends ViewModel {

    List<Contacts> contactsList = new List<Contacts>();

    public List<Contacts> generateContactsList() {

        Contacts ty = new Contacts();
        ty.setId(0);
        ty.setName("Lee");
        ty.setNumber(19950701);
        contactsList.add(ty);

        Contacts tl = new Contacts();
        tl.setId(1);
        tl.setName("Moon");
        tl.setNumber(19960614);
        contactsList.add(tl);

        Contacts jh = new Contacts();
        jh.setId(2);
        jh.setName("Johnny Soh");
        jh.setNumber(19950209);
        contactsList.add(jh);

        return contactsList;
    }


}
