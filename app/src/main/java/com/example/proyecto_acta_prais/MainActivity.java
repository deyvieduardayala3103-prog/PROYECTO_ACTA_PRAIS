package com.example.proyecto_acta_prais;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // Declaración de vistas
    private EditText etExpediente, etAgente, etCodigoOsi, etRegistroHidro,
            etHoraApertura, etHoraCierre, etDireccion, etDistrito, etProvincia,
            etDepartamento, etRuc, etTelefono, etDniFiscal, etNombreFiscal,
            etDieselPrice, etDieselPub, etDieselDisp, etDieselDesc,
            etG84Price, etG84Pub, etG84Disp, etG84Desc,
            etRegPrice, etRegPub, etRegDisp, etRegDesc,
            etPremPrice, etPremPub, etPremDisp, etPremDesc,
            etGlpPrice, etGlpPub, etGlpDisp, etGlpDesc,
            etCil3, etCil5, etCil10, etCil15, etCil45, etMarcaGLP,
            etHecho1, etHecho2, etHecho3, etHecho4, etHecho5, etHecho6,
            etOtras, etDocumentos, etObservaciones, etNegativa,
            etDniRecibe, etNombreRecibe, etRelacionRecibe;

    private TextView tvFecha;
    private Button btnFecha, btnVistaPrevia;
    private RadioGroup rgTelPub, rgTelReg, rgHorario;
    private String fechaSeleccionada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vincularVistas();

        // SELECCIONAR FECHA DE DILIGENCIA
        btnFecha.setOnClickListener(v -> {
            Calendar cal = Calendar.getInstance();
            int anio = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH);
            int dia = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(MainActivity.this,
                    (DatePicker view, int y, int m, int d) -> {
                        fechaSeleccionada = String.format("%02d/%02d/%04d", d, (m + 1), y);
                        tvFecha.setText("Fecha seleccionada: " + fechaSeleccionada);
                    }, anio, mes, dia);
            dpd.show();
        });

        // BOTÓN VISTA PREVIA / IMPRIMIR
        btnVistaPrevia.setOnClickListener(v -> {
            if (validarCamposObligatorios()) {
                generarVistaPrevia();
            }
        });
    }

    private void vincularVistas() {
        etExpediente = findViewById(R.id.etExpediente);
        etAgente = findViewById(R.id.etAgente);
        etCodigoOsi = findViewById(R.id.etCodigoOsi);
        etRegistroHidro = findViewById(R.id.etRegistroHidro);
        tvFecha = findViewById(R.id.tvFecha);
        btnFecha = findViewById(R.id.btnFecha);
        etHoraApertura = findViewById(R.id.etHoraApertura);
        etHoraCierre = findViewById(R.id.etHoraCierre);
        etDireccion = findViewById(R.id.etDireccion);
        etDistrito = findViewById(R.id.etDistrito);
        etProvincia = findViewById(R.id.etProvincia);
        etDepartamento = findViewById(R.id.etDepartamento);
        etRuc = findViewById(R.id.etRuc);
        etTelefono = findViewById(R.id.etTelefono);
        etDniFiscal = findViewById(R.id.etDniFiscal);
        etNombreFiscal = findViewById(R.id.etNombreFiscal);

        etDieselPrice = findViewById(R.id.etDieselPrice);
        etDieselPub = findViewById(R.id.etDieselPub);
        etDieselDisp = findViewById(R.id.etDieselDisp);
        etDieselDesc = findViewById(R.id.etDieselDesc);
        etG84Price = findViewById(R.id.etG84Price);
        etG84Pub = findViewById(R.id.etG84Pub);
        etG84Disp = findViewById(R.id.etG84Disp);
        etG84Desc = findViewById(R.id.etG84Desc);
        etRegPrice = findViewById(R.id.etRegPrice);
        etRegPub = findViewById(R.id.etRegPub);
        etRegDisp = findViewById(R.id.etRegDisp);
        etRegDesc = findViewById(R.id.etRegDesc);
        etPremPrice = findViewById(R.id.etPremPrice);
        etPremPub = findViewById(R.id.etPremPub);
        etPremDisp = findViewById(R.id.etPremDisp);
        etPremDesc = findViewById(R.id.etPremDesc);
        etGlpPrice = findViewById(R.id.etGlpPrice);
        etGlpPub = findViewById(R.id.etGlpPub);
        etGlpDisp = findViewById(R.id.etGlpDisp);
        etGlpDesc = findViewById(R.id.etGlpDesc);
        etCil3 = findViewById(R.id.etCil3);
        etCil5 = findViewById(R.id.etCil5);
        etCil10 = findViewById(R.id.etCil10);
        etCil15 = findViewById(R.id.etCil15);
        etCil45 = findViewById(R.id.etCil45);
        etMarcaGLP = findViewById(R.id.etMarcaGLP);

        rgTelPub = findViewById(R.id.rgTelPub);
        rgTelReg = findViewById(R.id.rgTelReg);
        rgHorario = findViewById(R.id.rgHorario);

        etHecho1 = findViewById(R.id.etHecho1);
        etHecho2 = findViewById(R.id.etHecho2);
        etHecho3 = findViewById(R.id.etHecho3);
        etHecho4 = findViewById(R.id.etHecho4);
        etHecho5 = findViewById(R.id.etHecho5);
        etHecho6 = findViewById(R.id.etHecho6);

        etOtras = findViewById(R.id.etOtras);
        etDocumentos = findViewById(R.id.etDocumentos);
        etObservaciones = findViewById(R.id.etObservaciones);
        etNegativa = findViewById(R.id.etNegativa);

        etDniRecibe = findViewById(R.id.etDniRecibe);
        etNombreRecibe = findViewById(R.id.etNombreRecibe);
        etRelacionRecibe = findViewById(R.id.etRelacionRecibe);

        btnVistaPrevia = findViewById(R.id.btnVistaPrevia);
    }

    private boolean validarCamposObligatorios() {
        if (etExpediente.getText().toString().trim().isEmpty()) {
            mostrarAlerta("Ingrese el número de expediente"); return false;
        }
        if (etAgente.getText().toString().trim().isEmpty()) {
            mostrarAlerta("Ingrese el Agente Fiscalizado"); return false;
        }
        if (etCodigoOsi.getText().toString().trim().isEmpty()) {
            mostrarAlerta("Ingrese el Código Osinergmin"); return false;
        }
        if (fechaSeleccionada.isEmpty()) {
            mostrarAlerta("Seleccione la Fecha de Diligencia"); return false;
        }
        if (etDireccion.getText().toString().trim().isEmpty()) {
            mostrarAlerta("Ingrese la Dirección"); return false;
        }
        if (etDistrito.getText().toString().trim().isEmpty()) {
            mostrarAlerta("Ingrese el Distrito"); return false;
        }
        if (etProvincia.getText().toString().trim().isEmpty()) {
            mostrarAlerta("Ingrese la Provincia"); return false;
        }
        if (etDniFiscal.getText().toString().trim().isEmpty()) {
            mostrarAlerta("Ingrese el DNI del Fiscalizador"); return false;
        }
        if (etNombreFiscal.getText().toString().trim().isEmpty()) {
            mostrarAlerta("Ingrese los datos del Fiscalizador"); return false;
        }
        return true;
    }

    private void mostrarAlerta(String mensaje) {
        new AlertDialog.Builder(this)
                .setTitle("⚠️ Faltan Datos")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .show();
    }

    private String getSiNo(RadioGroup rg) {
        int id = rg.getCheckedRadioButtonId();
        if (id == -1) {
            return "No especifica";
        }
        RadioButton rb = findViewById(id);
        return rb.getText().toString();
    }

    private void generarVistaPrevia() {
        String acta = "========================================\n" +
                "      ACTA DE FISCALIZACIÓN PRICE\n" +
                "========================================\n\n" +
                "I. DATOS DEL ESTABLECIMIENTO\n" +
                "Expediente:            " + etExpediente.getText() + "\n" +
                "Agente:                " + etAgente.getText() + "\n" +
                "Código OSINERGMIN:     " + etCodigoOsi.getText() + "\n" +
                "Registro Hidrocarburos:" + etRegistroHidro.getText() + "\n" +
                "Fecha:                 " + fechaSeleccionada + "\n" +
                "Hora Apertura:         " + etHoraApertura.getText() + "\n" +
                "Hora Cierre:           " + etHoraCierre.getText() + "\n" +
                "Dirección:             " + etDireccion.getText() + "\n" +
                "Distrito:              " + etDistrito.getText() + "\n" +
                "Provincia:             " + etProvincia.getText() + "\n" +
                "Departamento:          " + etDepartamento.getText() + "\n" +
                "RUC/DNI:               " + etRuc.getText() + "\n" +
                "Teléfono:              " + etTelefono.getText() + "\n\n" +

                "II. DATOS DEL FISCALIZADOR\n" +
                "DNI:    " + etDniFiscal.getText() + "\n" +
                "Nombre: " + etNombreFiscal.getText() + "\n\n" +

                "III. PRECIOS REGISTRADOS\n" +
                "Diésel B5: PRICE=" + etDieselPrice.getText() + " | Pub=" + etDieselPub.getText() + " | Disp=" + etDieselDisp.getText() + " | Desc=" + etDieselDesc.getText() + "\n" +
                "G-84:      PRICE=" + etG84Price.getText() + " | Pub=" + etG84Pub.getText() + " | Disp=" + etG84Disp.getText() + " | Desc=" + etG84Desc.getText() + "\n" +
                "Regular:   PRICE=" + etRegPrice.getText() + " | Pub=" + etRegPub.getText() + " | Disp=" + etRegDisp.getText() + " | Desc=" + etRegDesc.getText() + "\n" +
                "Premium:   PRICE=" + etPremPrice.getText() + " | Pub=" + etPremPub.getText() + " | Disp=" + etPremDisp.getText() + " | Desc=" + etPremDesc.getText() + "\n" +
                "GLP Auto:  PRICE=" + etGlpPrice.getText() + " | Pub=" + etGlpPub.getText() + " | Disp=" + etGlpDisp.getText() + " | Desc=" + etGlpDesc.getText() + "\n" +
                "GLP Cilindros: 3kg=" + etCil3.getText() + " 5kg=" + etCil5.getText() + " 10kg=" + etCil10.getText() + " 15kg=" + etCil15.getText() + " 45kg=" + etCil45.getText() + "\n" +
                "Marca GLP: " + etMarcaGLP.getText() + "\n\n" +

                "IV. VERIFICACIONES\n" +
                "Teléfono publicado: " + getSiNo(rgTelPub) + "\n" +
                "Teléfono en PRICE:   " + getSiNo(rgTelReg) + "\n" +
                "Horario publicado:   " + getSiNo(rgHorario) + "\n\n" +

                "V. HECHOS VERIFICADOS\n" +
                "1. " + etHecho1.getText() + "\n" +
                "2. " + etHecho2.getText() + "\n" +
                "3. " + etHecho3.getText() + "\n" +
                "4. " + etHecho4.getText() + "\n" +
                "5. " + etHecho5.getText() + "\n" +
                "6. " + etHecho6.getText() + "\n\n" +

                "VI. OTROS DATOS\n" +
                "Ocurrencias:    " + etOtras.getText() + "\n" +
                "Documentos:     " + etDocumentos.getText() + "\n" +
                "Observaciones:  " + etObservaciones.getText() + "\n" +
                "Negativa:       " + etNegativa.getText() + "\n\n" +

                "VII. DATOS DE QUIEN RECIBE EL ACTA\n" +
                "DNI:      " + etDniRecibe.getText() + "\n" +
                "Nombre:   " + etNombreRecibe.getText() + "\n" +
                "Relación: " + etRelacionRecibe.getText();

        new AlertDialog.Builder(this)
                .setTitle("📄 Vista Previa del Acta")
                .setMessage(acta)
                .setPositiveButton("Aceptar", null)
                .show();
    }
}