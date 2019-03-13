"
I'm the main component of the application. I group all component together  to provide the desired functionnality
"
Class {
	#name : #MMCMainPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'mooseModelPresenter',
		'mooseFormPresenter'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMainPresenter class >> defaultSpec [
	^ SpecTableLayout row
		add: #mooseModelPresenter;
		add: #mooseFormPresenter;
		yourself
]

{ #category : #specs }
MMCMainPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #initialization }
MMCMainPresenter >> initMooseFormPresenter [
	mooseFormPresenter := self instantiate: MMCMooseFormPresenter.
	mooseFormPresenter subscribeTo: context
]

{ #category : #initialization }
MMCMainPresenter >> initMooseModelPresenter [
	mooseModelPresenter := self instantiate: MMCMooseModelPresenter.
	mooseModelPresenter subscribeTo: context
]

{ #category : #initialization }
MMCMainPresenter >> initializeWidgets [
	self subscribeTo: MMCContext new.
	self initMooseModelPresenter.
	self initMooseFormPresenter.
]

{ #category : #accessing }
MMCMainPresenter >> mooseFormPresenter [
	^ mooseFormPresenter
]

{ #category : #accessing }
MMCMainPresenter >> mooseModelPresenter [
	^ mooseModelPresenter
]

{ #category : #subscription }
MMCMainPresenter >> update [
	
]
