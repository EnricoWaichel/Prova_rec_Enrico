package service.carros;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import exception.CarrosException;
import model.entity.carros.Carro;
import model.entity.carros.Montadora;
import model.seletor.carros.CarroSeletor;
import model.repository.CarroRepository;
import model.repository.MontadoraRepository;

public class CarroService {

    private CarroRepository carroRepository = new CarroRepository();
    private MontadoraRepository montadoraRepository = new MontadoraRepository();

    public ArrayList<Carro> consultarComFiltros(CarroSeletor seletor) {
        ArrayList<Carro> carros = carroRepository.consultarTodos();

        if (seletor.temFiltro()) {
            if (seletor.getModelo() != null && !seletor.getModelo().trim().isBlank()) {
                carros = filtrarPorModelo(carros, seletor.getModelo());
            }
            if (seletor.getNomeMarca() != null && !seletor.getNomeMarca().trim().isBlank()) {
                carros = filtrarPorNomeMarca(carros, seletor.getNomeMarca());
            }
            if (seletor.getAnoInicial() != null || seletor.getAnoFinal() != null) {
                carros = filtrarPorAno(carros, seletor);
            }
        }

        return carros;
    }

    private ArrayList<Carro> filtrarPorAno(ArrayList<Carro> carros, CarroSeletor seletor) {
        Predicate<Carro> intervalo;
        if (seletor.getAnoInicial() != null && seletor.getAnoFinal() != null) {
            intervalo = carro -> carro.getAno() >= seletor.getAnoInicial() && carro.getAno() <= seletor.getAnoFinal();
        } else if (seletor.getAnoInicial() != null) {
            intervalo = carro -> carro.getAno() >= seletor.getAnoInicial();
        } else {
            intervalo = carro -> carro.getAno() <= seletor.getAnoFinal();
        }

        return (ArrayList<Carro>) carros.stream()
                .filter(intervalo)
                .collect(Collectors.toList());
    }

    private ArrayList<Carro> filtrarPorNomeMarca(ArrayList<Carro> carros, String nomeMarca) {
        return (ArrayList<Carro>) carros.stream()
                    .filter(carro -> carro.getMontadora().getNome().equals(nomeMarca))
                    .collect(Collectors.toList());
    }

    private ArrayList<Carro> filtrarPorModelo(ArrayList<Carro> carros, String modelo) {
        return (ArrayList<Carro>) carros.stream()
                                    .filter(carro -> carro.getModelo().equals(modelo))
                                    .collect(Collectors.toList());
    }

    public int consultarEstoqueCarros(int idMontadora) throws CarrosException {
        ArrayList<Carro> carros = carroRepository.consultarTodos();
        Montadora montadora = montadoraRepository.consultarPorId(idMontadora);

        if (montadora == null) {
            throw new CarrosException("Montadora não encontrada. Id informado: " + idMontadora);
        }

        List<Carro> carrosDaMontadoraSelecionada = carros.stream()
                .filter(c -> c.getMontadora().getId() == idMontadora)
                .collect(Collectors.toList());

        return carrosDaMontadoraSelecionada.size();
    }
}
