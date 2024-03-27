public class Time {

    private int hours;          // Conventional hours
    private int minutes;        // Conventional minutes
    private boolean afternoon;  // Flag for afternoon

    /**
     * Constructs a customary time (12 hours, am or pm)
     * from a military time ##:##
     *
     * @param militaryTime Time in the military format ##:##
     */
    public Time(String militaryTime) {
        // Check if something was entered
        if (militaryTime == null) {
            System.out.println("Invalid military time: null");
            return;
        }

        // Check if there are 5 characters
        if (militaryTime.length() != 5) {
            System.out.println("Invalid military time: length must be 5 characters");
            return;
        }

        // Check if the colon is in the correct spot
        if (militaryTime.charAt(2) != ':') {
            System.out.println("Invalid military time: colon must be at index 2");
            return;
        }

        // Check if all other characters are digits
        for (int i = 0; i < militaryTime.length(); i++) {
            if (i != 2 && !Character.isDigit(militaryTime.charAt(i))) {
                System.out.println("Invalid military time: all characters except colon must be digits");
                return;
            }
        }

        // Separate the string into hours and minutes, converting them to integers
        {
            hours = Integer.parseInt(militaryTime.substring(0, 2));
            minutes = Integer.parseInt(militaryTime.substring(3));

            // Validate hours and minutes are valid values
            if (hours > 23 || minutes > 59) {
                System.out.println("Invalid military time: hours and/or minutes out of range");
                return;
            }

            // Convert military time to conventional time for afternoon times
            if (hours > 12) {
                hours -= 12;
                afternoon = true;
            }

            // Account for midnight
            if (hours == 0) {
                hours = 12;
            }

            // Account for noon
            if (hours == 12) {
                afternoon = true;
            }

            // Display the conventional time
            System.out.println(this.toString());
        }   {
            System.out.println("Invalid military time: hours and minutes must be integers");
        }
    }

    /**
     * The toString method returns a conventional time.
     *
     * @return A conventional time with am or pm.
     */
    public String toString() {
        String am_pm = afternoon ? "PM" : "AM";
        String zero = (minutes < 10) ? "0" : "";
        return hours + ":" + zero + minutes + " " + am_pm;
    }

    public static void main(String[] args) {
        // Example usage
        Time time = new Time("12:30");
        System.out.println("Conventional Time: " + time.toString());
    }
}

