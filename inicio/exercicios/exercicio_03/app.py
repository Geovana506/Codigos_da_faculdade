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
            n = int(request.form["numero"])

            par = "Par" if n % 2 == 0 else "Ímpar"

            if n > 0:
                sinal = "Positivo"
            elif n < 0:
                sinal = "Negativo"
            else:
                sinal = "Zero"

            resultado = f"O número {n} é {par} e {sinal}"

    except ValueError:
        erro = "Digite um número válido!"

    return render_template("index.html", resultado=resultado, erro=erro)


if __name__ == "__main__":
    app.run(debug=True)
