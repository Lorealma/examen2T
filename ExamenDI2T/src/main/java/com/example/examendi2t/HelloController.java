package com.example.examendi2t;

import clase.Alumno;
import clase.Modulo;
import dao.AlumnoDAO;
import dao.ModuloDAO;
import errores.NotaException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField tf_nombre;
    @FXML
    private TextField tf_apellido;
    @FXML
    private TextField tf_correo;
    @FXML
    private TextField tf_dni;
    @FXML
    private TextField tf_telefono;
    @FXML
    private TextField tf_localidad;
    @FXML
    private TextField tf_ad;
    @FXML
    private TextField tf_sge;
    @FXML
    private TextField tf_pmdm;
    @FXML
    private TextField tf_psp;
    @FXML
    private TextField tf_eie;
    @FXML
    private TextField tf_di;
    @FXML
    private TextField tf_hlc;
    @FXML
    private Button btn_introducir;
    @FXML
    private Button btn_descargar;
    @FXML
    private Button btn_salir;
    @FXML
    private TableView<Alumno> tv_tabla;
    @FXML
    private TableColumn<Alumno, String> col_nombre;
    @FXML
    private TableColumn<Alumno, String> col_apellido;
    @FXML
    private TableColumn<Alumno, String> col_correo;
    @FXML
    private TableColumn<Alumno, String> col_dni;
    @FXML
    private TableColumn<Alumno, String> col_telefono;
    @FXML
    private TableColumn<Alumno, String> col_fechaNacimiento;
    @FXML
    private TableColumn<Alumno, String> col_localidad;
    @FXML
    private TableColumn<Alumno, String> col_ad;
    @FXML
    private TableColumn<Alumno, String> col_sge;
    @FXML
    private TableColumn<Alumno, String> col_pmdm;
    @FXML
    private TableColumn<Alumno, String> col_psp;
    @FXML
    private TableColumn<Alumno, String> col_eie;
    @FXML
    private TableColumn<Alumno, String> col_di;
    @FXML
    private TableColumn<Alumno, String> col_hlc;
    @FXML
    private DatePicker dp_fechaNacimiento;


    @FXML
    public void introducirAlumno(ActionEvent actionEvent) {

        if(!tf_nombre.getText().isEmpty()&&!tf_apellido.getText().isEmpty()&&!tf_correo.getText().isEmpty()
                &&!tf_dni.getText().isEmpty()&&!dp_fechaNacimiento.getValue().equals("")&&!tf_dni.getText().isEmpty()
                &&!tf_telefono.getText().isEmpty()&&!tf_localidad.getText().isEmpty()&&!tf_ad.getText().isEmpty()
                &&!tf_sge.getText().isEmpty()&&!tf_pmdm.getText().isEmpty()&&!tf_psp.getText().isEmpty()
                &&!tf_eie.getText().isEmpty()&&!tf_di.getText().isEmpty()&&!tf_hlc.getText().isEmpty()) {

            //AHORA HAY QUE COMPROBAR QUE LAS NOTAS ESTEN ENTRE 0 y 10 PODEMOS HACERLO EN EL CONTROLADOR DE ARRIBA O AHORA POR SEPARADO

            //CASTEAMOS LAS NOTAS

            try {  //CONTROLAMOS ERROR PORQUE SI SE INTRODUJERAN LETRAS EN LOS CAMPOS DARIA ERROR AL SER INTEGER

                /*Integer notaAD = Integer.valueOf(tf_ad.getText());
                Integer notaSGE = Integer.valueOf(tf_sge.getText());
                Integer notaPMDM = Integer.valueOf(tf_pmdm.getText());
                Integer notaPSP = Integer.valueOf(tf_psp.getText());
                Integer notaEIE = Integer.valueOf(tf_eie.getText());
                Integer notaDI = Integer.valueOf(tf_di.getText());
                Integer notaHLC = Integer.valueOf(tf_hlc.getText());
*/

                //Y HACEMOS EL CONTROL

                /*if (notaAD >= 0 && notaSGE >= 0 && notaPMDM >= 0 && notaPSP >= 0 && notaEIE >= 0 && notaDI >= 0 && notaHLC >= 0 && notaAD <= 10
                        && notaSGE <= 10 && notaPMDM <= 10 && notaPSP <= 10 && notaEIE <= 10 && notaHLC <= 10) {*/

                    //CREAMOS UN NUEVO ALUMNO

                String[] apellidos = tf_apellido.getText().split(" ");

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                String fechaFormateada = dp_fechaNacimiento.getValue().format(formato);

                LocalDate local = LocalDate.parse(fechaFormateada);

                Date fechaReal = Date.valueOf(local);

                try {
                    Modulo modulo = new Modulo(Long.valueOf(tf_ad.getText()),Long.valueOf(tf_sge.getText()),Long.valueOf(tf_di.getText()),
                            Long.valueOf(tf_pmdm.getText()),Long.valueOf(tf_psp.getText()),Long.valueOf(tf_eie.getText()),Long.valueOf(tf_hlc.getText()));

                    modulo = ModuloDAO.insertarModulo(modulo);

                    Alumno alumno=new Alumno(tf_nombre.getText(),apellidos[0],apellidos[1],tf_correo.getText(),tf_dni.getText(),
                            fechaReal,tf_localidad.getText(),tf_telefono.getText(), Math.toIntExact(modulo.getId()),modulo);
                    alumno=AlumnoDAO.insertarAlumno(alumno);
                    tv_tabla.getItems().add(alumno);


                } catch (NotaException e) {
                    throw new RuntimeException(e);
                }



                /*Alumno alumno = new Alumno(tf_nombre.getText(), apellidos[0], apellidos[1], tf_correo.getText(), tf_dni.getText(),
                            tf_telefono.getText(), fechaReal, tf_localidad.getText());
                    alumno =


                    //INSERCION DE LA BBDD

                    AlumnoDAO.insertarAlumno(alumno);*/

               /* } else {  //CREAMOS VENTANAS DE ALERTA PARA QUE EL USUARIO SEPA SUS FALLOS AL SALIR ERROR

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Las notas deben estar en el intervalo [0-10].");
                    alert.showAndWait();
                }*/

            }catch(NumberFormatException e){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Asegúrate de insertar bien las notas.");
                alert.showAndWait();

            }

        }else{

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Todos los campos deben estar llenos.");
            alert.showAndWait();
        }


    }

    @FXML
    public void descargarNotas(ActionEvent actionEvent) {
    }

    @FXML
    public void salir(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_nombre.setCellValueFactory((alumno)->{

            String nombreAlumno = alumno.getValue().getNombre();
            return new SimpleStringProperty(nombreAlumno);

        });

        col_apellido.setCellValueFactory((alumno)->{

            String apellidoAlumno = alumno.getValue().getApellido1();
            return new SimpleStringProperty(apellidoAlumno);

        });

        col_correo.setCellValueFactory((alumno)->{

            String apellidoAlumno = alumno.getValue().getCorreo();
            return new SimpleStringProperty(apellidoAlumno);

        });

        col_dni.setCellValueFactory((alumno)->{

            String apellidoAlumno = alumno.getValue().getDni();
            return new SimpleStringProperty(apellidoAlumno);

        });


        col_telefono.setCellValueFactory((alumno)->{

            String telefono = alumno.getValue().getTelefono();
            return new SimpleStringProperty(telefono);

        });

        col_fechaNacimiento.setCellValueFactory((alumno)->{

            String fechaNacimiento = String.valueOf(alumno.getValue().getFecha());
            return new SimpleStringProperty(fechaNacimiento);

        });

        col_localidad.setCellValueFactory((alumno)->{

            String localidad = alumno.getValue().getLocalidad();
            return new SimpleStringProperty(localidad);

        });

        col_ad.setCellValueFactory((alumno)->{

            String adAlumno = String.valueOf(alumno.getValue().getModulo().getAd());
            return new SimpleStringProperty(adAlumno);

        });

        col_sge.setCellValueFactory((alumno)->{

            String sgeAlumno = String.valueOf(alumno.getValue().getModulo().getSge());
            return new SimpleStringProperty(sgeAlumno);

        });

        col_pmdm.setCellValueFactory((alumno)->{

            String pmdmAlumno = String.valueOf(alumno.getValue().getModulo().getPmdm());
            return new SimpleStringProperty(pmdmAlumno);

        });

        col_psp.setCellValueFactory((alumno)->{

            String pspAlumno = String.valueOf(alumno.getValue().getModulo().getPsp());
            return new SimpleStringProperty(pspAlumno);

        });

        col_eie.setCellValueFactory((alumno)->{

            String eieAlumno = String.valueOf(alumno.getValue().getModulo().getEie());
            return new SimpleStringProperty(eieAlumno);

        });

        col_di.setCellValueFactory((alumno)->{

            String diAlumno = String.valueOf(alumno.getValue().getModulo().getDi());
            return new SimpleStringProperty(diAlumno);

        });

        col_hlc.setCellValueFactory((alumno)->{

            String hlcAlumno = String.valueOf(alumno.getValue().getModulo().getHlc());
            return new SimpleStringProperty(hlcAlumno);

        });





    }
}