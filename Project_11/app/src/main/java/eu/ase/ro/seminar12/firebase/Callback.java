package eu.ase.ro.seminar12.firebase;

public interface Callback<R> {
    void runResultOnUiThread(R result);
}
