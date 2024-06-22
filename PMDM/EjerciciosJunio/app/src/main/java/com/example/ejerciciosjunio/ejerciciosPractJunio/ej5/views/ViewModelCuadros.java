package com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.views;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.data.Cuadro;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.data.Cuadros;
import com.example.ejerciciosjunio.ejerciciosPractJunio.ej5.data.ServiceCuadros;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelCuadros extends ViewModel {
    private static final double DELAY = 2000;
    private MutableLiveData<List<Cuadros>> cuadrosMLD;
    private MutableLiveData<Cuadro> cuadroMLD;

    public LiveData<List<Cuadros>> getCuadros() {
        if (cuadrosMLD == null){
            cuadrosMLD = new MutableLiveData<List<Cuadros>>();
        }
        return cuadrosMLD;
    }
    public LiveData<Cuadro> getCuadro() {
        if (cuadroMLD == null){
            cuadroMLD = new MutableLiveData<Cuadro>();
        }
        return cuadroMLD;
    }

    public void cargaCuadros() {
        new Thread(
                ()->{
                    try {
                        Thread.sleep((long) (DELAY));
                        ServiceCuadros ser = ServiceCuadros.getInstancia();
                        Call<List<Cuadros>> llamada = ser.getRepoCuadro().getCuadros();
                        llamada.enqueue(new Callback<List<Cuadros>>() {
                            @Override
                            public void onResponse(Call<List<Cuadros>> call, Response<List<Cuadros>> response) {
                                if (response.isSuccessful()) {
                                    cuadrosMLD.postValue(response.body());
                                } else {
                                    System.out.println("Error al cargar los datos");
                                    //Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Cuadros>> call, Throwable t) {
                                System.out.println("Fallo en la conexion");
                            }
                        });
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).start();
    }
    public void cargaCuadro(int id){
        new Thread(
                ()->{
                    try {
                        Thread.sleep((long) (DELAY));
                        ServiceCuadros ser = ServiceCuadros.getInstancia();
                        Call <Cuadro> llamada = ser.getRepoCuadro().getCuadro(id);
                        llamada.enqueue(new Callback<Cuadro>() {
                            @Override
                            public void onResponse(Call<Cuadro> call, Response<Cuadro> response) {
                                if (response.isSuccessful()) {
                                    //Cuadro cuadro = response.body();
                                    cuadroMLD.postValue(response.body());
                                } else {
                                    System.out.println("Error al cargar los datos");
                                    //Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<Cuadro> call, Throwable t) {
                                System.out.println("Fallo en la conexion");
                            }
                        });
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).start();
    }
}