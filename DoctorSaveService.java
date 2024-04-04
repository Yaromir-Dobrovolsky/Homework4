package org.example.service;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Класс для сохранения информации о докторе
public class DoctorSaveService {

    // Внутренний класс, представляющий доктора
    public static class Doctor implements Externalizable {
        public String name;
        public String specialization;

        public String getName() {
            return name;
        }

        public String getSpecialization() {
            return specialization;
        }

        public int getExperience() {
            return experience;
        }

        public double getHeight() {
            return height;
        }

        public boolean isNew() {
            return isNew;
        }

        public int experience;
        public double height;
        public boolean isNew;

        // Пустой конструктор
        public Doctor() {
        }

        // Метод для записи объекта в поток
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(name);
            out.writeObject(specialization);
            out.writeInt(experience);
            out.writeDouble(height);
            out.writeBoolean(isNew);
        }

        // Метод для чтения объекта из потока
        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            name = (String) in.readObject();
            specialization = (String) in.readObject();
            experience = in.readInt();
            height = in.readDouble();
            isNew = in.readBoolean();
        }
    }
}
