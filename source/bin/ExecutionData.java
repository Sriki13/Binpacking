package bin;

import java.util.ArrayList;
import java.util.List;

/**
 * Une trace d'exécution d'un algorithme sur plusieurs simulations.
 */
@SuppressWarnings("ConstantConditions")
public class ExecutionData {

    private List<Long> filledBins;
    private List<Long> nbWrite;
    private List<Long> nbRead;
    private List<Long> execTime;

    public ExecutionData() {
        this.filledBins = new ArrayList<>();
        this.nbWrite = new ArrayList<>();
        this.nbRead = new ArrayList<>();
        this.execTime = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\tNombre de simulations : ").append(filledBins.size()).append('\n');
        createResult(sb, "de bins utilisés", filledBins);
        createResult(sb, "du temps d'éxécution en nanosecondes", execTime);
        if (!nbRead.isEmpty()) {
            createResult(sb, "du nombre de lectures", nbRead);
        }
        if (!nbWrite.isEmpty()) {
            createResult(sb, "du nombre d'écritures", nbWrite);
        }
        return sb.toString();
    }

    private void createResult(StringBuilder sb, String type, List<Long> data) {
        sb.append("\t\tMoyenne ").append(type).append(" : ");
        sb.append(Math.round(data.stream().mapToDouble(value -> value).average().getAsDouble())).append('\n');
        sb.append("\t\tMinimum ").append(type).append(" : ");
        sb.append(data.stream().min(Long::compareTo).get()).append('\n');
        sb.append("\t\tMaximum ").append(type).append(" : ");
        sb.append(data.stream().max(Long::compareTo).get()).append('\n');
        sb.append('\n');
    }

    public void addExecTime(Long execTime) {
        this.execTime.add(execTime);
    }

    public void addFilledBins(Integer filledBins) {
        this.filledBins.add(Long.valueOf(filledBins));
    }

    public void addnbWrite(Long nbWrite) {
        this.nbWrite.add(nbWrite);
    }

    public void addnbRead(Long nbRead) {
        this.nbRead.add(nbRead);
    }

}
