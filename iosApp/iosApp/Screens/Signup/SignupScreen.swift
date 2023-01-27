//
//  SignupScreen.swift
//  iosApp
//
//  Created by Quipper Indonesia on 27/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SignupScreen: View {
    @StateObject private var viewModel = ViewModelProvider().provideSignupViewModel()
    
    var body: some View {
        VStack{
            Text("Signup new account")
                .font(.largeTitle)
                .padding([.top, .bottom], 40)
                .shadow(radius: 10.0, x: 20, y: 10)
            
            VStack (
                alignment: .leading,
                spacing: 15
            ) {
                TextFieldWithPromptView(
                    sfSymbolName: "mail",
                    placeholder: "Email",
                    text: $viewModel.viewState.email,
                    prompt:  viewModel.viewState.emailError
                ).autocapitalization(.none)
                    .disabled(viewModel.viewState.isLoading)
                
                TextFieldWithPromptView(
                    sfSymbolName: "person",
                    placeholder: "Username",
                    text: $viewModel.viewState.username,
                    prompt:  viewModel.viewState.usernameError
                ).autocapitalization(.none)
                    .disabled(viewModel.viewState.isLoading)
                PasswordView(
                    placeholder: "Password",
                    text: $viewModel.viewState.password,
                    prompt: viewModel.viewState.passwordError
                ).disabled(viewModel.viewState.isLoading)
                
                PasswordView(
                    placeholder: "Confirmation Password",
                    text: $viewModel.viewState.confirmationPassword,
                    prompt: viewModel.viewState.confirmationPassword
                ).disabled(viewModel.viewState.isLoading)
                
            }.padding([.leading, .trailing], 27.5)
            
            VStack {
                Button(
                    action: {
                        
                    }
                ) {
                    HStack {
                        Text("Signup")
                            .font(.headline)
                            .foregroundColor(.white)
                        
                        if viewModel.viewState.isLoading {
                            ProgressView()
                        }
                    }.padding()
                        .frame(width: 300, height: 50)
                        .background(Color.green)
                        .cornerRadius(15.0)
                        .shadow(radius: 10.0, x: 20, y: 10)
                }.padding(.top, 50)
            }
            
            Spacer()
        }.background(
            LinearGradient(
                gradient: Gradient(
                    colors: [.purple, .blue]
                ), startPoint: .top, endPoint: .bottom
            ).edgesIgnoringSafeArea(.all)
        )
    }
}

struct SignupScreen_Previews: PreviewProvider {
    static var previews: some View {
        SignupScreen()
    }
}