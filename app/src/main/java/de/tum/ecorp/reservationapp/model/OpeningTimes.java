package de.tum.ecorp.reservationapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OpeningTimes  {

    private Map<Integer, Set<TimeSlot>> openingTimes;

    public OpeningTimes() {
        this.openingTimes = new HashMap<>();
    }

    protected OpeningTimes(Parcel in) {
    }

    public void addTimeSlot(int weekday, TimeSlot timeSlot) {
        getTimeSlots(weekday).add(timeSlot);
    }

    public String toString() {

        String result = new String();
        for (Iterator<Map.Entry<Integer, Set<TimeSlot>>> iterator = openingTimes.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Integer, Set<TimeSlot>> entry = iterator.next();

            String weekday = summarizeTimeSlots(entry.getValue());

            if (!weekday.isEmpty()) {
                result += weekday + "\n";
            }
        }

        return result;
    }

    public List<String> toStringList() {
        List<String> result = new ArrayList<>();

        for (Iterator<Map.Entry<Integer, Set<TimeSlot>>> iterator = openingTimes.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Integer, Set<TimeSlot>> entry = iterator.next();

            result.add(summarizeTimeSlots(entry.getValue()));
        }

        return result;
    }

    public String summarizeTimeSlots(Set<TimeSlot> timeSlots) {

        List<TimeSlot> slotList = new ArrayList<>(timeSlots);
        Collections.sort(slotList);

        String result = new String();
        for (Iterator<TimeSlot> it = slotList.iterator(); it.hasNext(); ) {
            TimeSlot ts = it.next();
            result += ts.toString();

            if (it.hasNext()) {
                result += ", ";
            }
        }

        return result; //TODO: Adjust format
    }

    public Set<TimeSlot> getTimeSlots(int weekday) {
        Set<TimeSlot> result = this.openingTimes.get(weekday);
        if (result == null) {
            this.openingTimes.put(weekday, new HashSet<TimeSlot>());
        }
        return this.openingTimes.get(weekday);
    }


}
