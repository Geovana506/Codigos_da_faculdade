import os
from flask import Flask, render_template, request

app = Flask(__name__, static_folder=os.path.join(
    os.path.dirname(__file__), "..", "static"))


@app.route("/", methods=["GET", "POST"])
def index():
    resultado = None
    erro = None

    try:
        if request.method == "POST":
            tipo = request.form.get("tipo")

            capital = float(request.form["capital"])
            taxa = float(request.form["taxa"])
            tempo = int(request.form["tempo"])

            if capital <= 0 or tempo <= 0:
                erro = "Valores devem ser maiores que zero."
            else:
                if tipo == "simples":
                    juros = capital * (taxa/100) * tempo
                    montante = capital + juros

                    resultado = {
                        "capital": round(capital, 2),
                        "juros": round(juros, 2),
                        "montante": round(montante, 2)
                    }

                elif tipo == "compostos":
                    montante = capital * (1 + taxa/100) ** tempo
                    resultado = {
                        "montante": round(montante, 2)
                    }

    except ValueError:
        erro = "Digite números válidos!"

    return render_template("index.html", resultado=resultado, erro=erro)


if __name__ == "__main__":
    app.run(debug=True)
