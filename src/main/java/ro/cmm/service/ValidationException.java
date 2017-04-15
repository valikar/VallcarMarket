package ro.cmm.service;

import java.util.Arrays;

/**
 * Created by Joseph Saturday, 15.04.2017 at 12:37.
 */
public class ValidationException extends Exception {
    private String[] causes;

    public ValidationException(String... causes) {
        super();
        this.causes = causes;
    }

    @Override
    public String getMessage() {

        return causes != null ? Arrays.toString(causes) : "No CAUSE!";
    }

    public String[] getCauses() {
        return causes;
    }

    public void setCauses(String[] causes) {
        this.causes = causes;
    }

}
