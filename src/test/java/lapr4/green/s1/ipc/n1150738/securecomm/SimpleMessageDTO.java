package lapr4.green.s1.ipc.n1150738.securecomm;

import java.io.Serializable;

/**
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class SimpleMessageDTO implements Serializable{

    private String message;

    public SimpleMessageDTO(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleMessageDTO that = (SimpleMessageDTO) o;

        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }
}
