package controller;

// =====================================================
// Custom Exception — represents all Garage-specific errors
// Why: instead of generic IllegalArgumentException,
//      we have our own exception that clearly says "this is a Garage problem"
// =====================================================
public class GarageException extends Exception {

    public GarageException(String message) {
        super(message);
    }
}