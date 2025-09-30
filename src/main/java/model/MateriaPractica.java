package model;

public class MateriaPractica extends Materia {
    private byte horasPractica;
    private byte numeroLabs;

    public MateriaPractica(String codigo, String nombre, byte numeroHoras, byte cantidadCreditos, String semestre,String profesor, Carrera ownedByCarrera, byte horasPractica, byte numeroLabs) {
        super(codigo, nombre, numeroHoras, cantidadCreditos, semestre,profesor, ownedByCarrera);
        this.horasPractica = horasPractica;
        this.numeroLabs = numeroLabs;
    }

    public byte getHorasPractica() {
        return horasPractica;
    }

    public void setHorasPractica(byte horasPractica) {
        this.horasPractica = horasPractica;
    }

    public byte getNumeroLabs() {
        return numeroLabs;
    }

    public void setNumeroLabs(byte numeroLabs) {
        this.numeroLabs = numeroLabs;
    }

    @Override
    public String toString() {
        return "MateriaPractica{" +
                "horasPractica=" + horasPractica +
                ", numeroLabs=" + numeroLabs +
                '}';
    }
}
